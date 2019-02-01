package xyz.nietongxue.serviceql.service

import com.cv4j.netdiscovery.core.Spider
import com.cv4j.netdiscovery.core.domain.Request
import com.cv4j.netdiscovery.core.parser.selector.XpathSelector
import us.codecraft.xsoup.Xsoup


object DubboAdmin {

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
                        //Fixme 加入批量操作。随机休息。
                        ).take(100).forEach {
                            println(it)
                            ApplicationsPage(it).run()
                        }
                    }.run()
        }
    }

    class ApplicationsPage(val service: String) {
        fun run() = Spider.create().request(adminReqest("/services/$service/applications"))
                .parser { page ->
                    println("Service - $service")
                    Xsoup.compile("//table[@id=table_o]//font[@color=blue]")
                            .evaluate(page.html.document).elements.map {
                        val value = it.parent().parent().select("td:eq(1)").text()

                        println("Consumer - $value")
                    }
                    Xsoup.compile("//table[@id=table_o]//font[@color=green]")
                            .evaluate(page.html.document).elements.map {
                        val value = it.parent().parent().select("td:eq(1)").text()
                        println("Provider - $value")
                    }
                }.run()
    }
}