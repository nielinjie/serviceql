package xyz.nietongxue.serviceql.service

import kotlinx.coroutines.delay
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.Date

@Component
class Task(val serviceRepository: ServiceRepository) {
    //    @Scheduled(fixedRate = 60000)
//    fun timer() {
//
//        println("Timer is saying - " + Date().toGMTString())
//    }
    @Scheduled(fixedDelay = 60000, initialDelay = 2000)
    fun spider() {
        DubboAdmin.serviceRepository = this.serviceRepository
        DubboAdmin.HomePage().run()
    }
}

//fun main() {
//    Task(ServiceRepository(ApplicationRepository())).spider()
//}