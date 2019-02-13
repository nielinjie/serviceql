package xyz.nietongxue.serviceql.service

import com.cv4j.netdiscovery.core.Spider
import com.cv4j.netdiscovery.core.domain.Request
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import xyz.nietongxue.serviceql.bean
import xyz.nietongxue.serviceql.jsonPath

@Component
class RabbitMqConsole {

    @Value("\${rabbit.console.url}")
    var url: String? = null
    @Value("\${rabbit.console.auth}")
    var auth: String? = null

    fun consoleRequest(path: String): Request = Request("$url/$path").header(
            "Authorization",
            "Basic $auth"
    )


}


class BindingPage {
    fun run() {
        Spider.create().request(bean<RabbitMqConsole>()!!.consoleRequest("bindings"))
                .parser { page ->
                    val issueR = bean<IssueRepository>()!!
                    page.jsonPath("""$[*]""").forEach {
                        println(it)
                        val source = it.jsonPath("source").first()
                        val destination = it.jsonPath("destination").first()
                        println("source - $source")
                        println("destination - $destination")
                        if (source.isNotBlank()) {
                            Name(source).let {
                                if (it.issues.isNotEmpty()) {
                                    it.issues.map {
                                        "source name $it".let {
                                            Issue(it, it, 3, null)
                                        }
                                    }.forEach {
                                        issueR.add(it)
                                    }
                                }
                            }
                        }
                        Name(destination).let {
                            if (it.issues.isNotEmpty()) {
                                it.issues.map {
                                    "destination name $it".let {
                                        Issue(it, it, 3, null)
                                    }
                                }.forEach {
                                    issueR.add(it)
                                }
                            }
                        }
                        if (source.isNotBlank()) {
                            bean<ServiceRepository>()!!.addFromRabbit(Name(source), Name(destination))
                        } else {
                            val issue = Issue(
                                    "no event source - destination = $destination",
                                    "no event source - destination = $destination",
                                    5,
                                    null
                            )
                            issueR.add(issue)
                        }
                    }
                }.run()
    }
}

class Name(val string: String) {
    var nomarlized: List<String> = emptyList()
    var nomarl: Boolean = false
    var issues: List<String> = emptyList()

    init {
        if (string.contains("_")) {
            val parts = string.split("_")
            nomarlized = parts.toList()
            nomarl = true
        } else {
            val parts = camel(string)
            nomarlized = parts
            if (parts.size >= 2) {
                nomarl = true
            } else {
                issues = listOf(
                        "not normal - $string"
                )
            }
        }
    }

    private fun camel(s: String): List<String> {
        return s.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])".toRegex()).dropLastWhile { it.isEmpty() }
    }
}



