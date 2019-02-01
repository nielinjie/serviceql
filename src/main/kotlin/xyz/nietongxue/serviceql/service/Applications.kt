package xyz.nietongxue.serviceql.service

import org.springframework.stereotype.Component

@Component
class ApplicationRepository {
    fun a() = Application("a", "A")
    fun b() = Application("b", "B")

}

data class Application(val id: String, val name: String)