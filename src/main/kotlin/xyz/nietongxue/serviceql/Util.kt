package xyz.nietongxue.serviceql

import com.cv4j.netdiscovery.core.config.Constant
import com.cv4j.netdiscovery.core.domain.Page
import com.cv4j.netdiscovery.core.parser.selector.Json
import com.cv4j.netdiscovery.core.parser.selector.JsonPathSelector

inline fun <reified T> bean(): T? {
    return ApplicationBootConfiguration.context.getBean(T::class.java)
}


fun Page.jsonPath(path: String): List<String> {
    return (this.getField(Constant.RESPONSE_JSON) as Json).jsonPath(path).all()
}
fun String.jsonPath(path: String): List<String> {
    return JsonPathSelector(path).selectList(this)
}