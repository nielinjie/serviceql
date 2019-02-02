package xyz.nietongxue.serviceql.service

import org.springframework.stereotype.Component

@Component
class ApplicationRepository {
    private val apps = mutableListOf<Application>(
//            Application("a", "A"),
//                    Application("b", "B")
    )

    fun getByName(name:String):Application? = apps.find { it.name == name }
    fun add(application: Application){
        this.apps.add(application)
    }
}

data class Application(val id: String, val name: String)