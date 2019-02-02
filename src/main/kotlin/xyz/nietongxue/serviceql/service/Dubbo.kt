package xyz.nietongxue.serviceql.service

import com.cv4j.netdiscovery.core.Spider
import com.cv4j.netdiscovery.core.domain.Request
import com.cv4j.netdiscovery.core.parser.selector.XpathSelector
import us.codecraft.xsoup.Xsoup
import java.util.*
import kotlin.random.Random


object DubboAdmin {
    var serviceRepository: ServiceRepository? = null
    fun adminReqest(path: String): Request = Request("http://10.101.0.103:8080/governance/$path").header(
            "Authorization",
            "Basic cm9vdDpyb290"
    )

    class HomePage() {
        fun run() {
            Spider.create().request(adminReqest("/services"))
                    .parser { page ->
                        page.html.selectDocumentForList(
                                XpathSelector("//table[@id=table_o]//a/text()")
                        )
                                //TODO 生产环境删除。
                                .take(50)
                                .forEach {
                                    println(it)
                                    ApplicationsPage(it).run()
                                }
                    }.run()
        }
    }

    class ApplicationsPage(val service: String) {
        fun run() = Spider.create().initialDelay(Random(Date().time).nextLong(0L, 300L)).request(adminReqest("/services/$service/applications"))
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

                    serviceRepository?.addFromDubbo(service,providers,consumers)
                }.run()
    }
}