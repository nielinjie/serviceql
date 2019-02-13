package xyz.nietongxue.serviceql.service

import com.cv4j.netdiscovery.core.Spider
import com.cv4j.netdiscovery.core.domain.Request
import com.cv4j.netdiscovery.core.parser.selector.XpathSelector
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import us.codecraft.xsoup.Xsoup
import xyz.nietongxue.serviceql.bean
import java.util.*
import kotlin.random.Random

@Component
class DubboAdmin {

    @Value("\${dubbo.admin.url}")
    var url: String? = null
    @Value("\${dubbo.admin.auth}")
    var auth: String? = null

    fun adminRequest(path: String): Request = Request("$url/$path").header(
            "Authorization",
            "Basic $auth"
    )


}

class HomePage {
    fun run() {
        Spider.create().request(bean<DubboAdmin>()!!.adminRequest("/services"))
                .parser { page ->
                    page.html.selectDocumentForList(
                            XpathSelector("//table[@id=table_o]//a/text()")
                    )
                            //TODO 生产环境删除。
                            .filter { it.endsWith(".dev") }
                            .forEach {
                                ApplicationsPage(it).run()
                            }
                }.run()
    }
}

class ApplicationsPage(val service: String) {
    fun run() = Spider.create().initialDelay(Random(Date().time).nextLong(0L, 300L))
            .request(bean<DubboAdmin>()!!.adminRequest("/services/$service/applications"))
            .parser { page ->
                println("Service - $service")
                val providers: MutableList<String> = mutableListOf()
                val consumers: MutableList<String> = mutableListOf()
                Xsoup.compile("//table[@id=table_o]//font[@color=blue]")
                        .evaluate(page.html.document).elements.map {
                    val value = it.parent().parent().select("td:eq(1)").text()
                    consumers.add(value)
                    println("Consumer - $value")
                }
                Xsoup.compile("//table[@id=table_o]//font[@color=green]")
                        .evaluate(page.html.document).elements.map {
                    val value = it.parent().parent().select("td:eq(1)").text()
                    providers.add(value)
                    println("Provider - $value")
                }

                bean<ServiceRepository>()!!.addFromDubbo(service, providers, consumers)
            }.run()
}