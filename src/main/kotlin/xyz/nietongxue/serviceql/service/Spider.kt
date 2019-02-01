package xyz.nietongxue.serviceql.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.Date

@Component
class Task {
//    @Scheduled(fixedRate = 60000)
    fun timer() {

        println("Timer is saying - "+Date().toGMTString())
    }

    fun spider(){
        DubboAdmin.HomePage().run()
    }
}
fun main(){
    Task().spider()
}