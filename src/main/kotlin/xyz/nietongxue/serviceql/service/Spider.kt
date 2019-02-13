package xyz.nietongxue.serviceql.service

import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.Date

@Component
class Spider(val serviceRepository: ServiceRepository, val issueRepository: IssueRepository, val linkRepository: LinkRepository) {



    @Scheduled(fixedDelay = 1000L * 60L * 60L, //1hour
            initialDelay = 7000)
    fun spider() {
        HomePage().run()
    }




    @Scheduled(fixedDelay = 1000L * 60L * 60L, //1hour
            initialDelay = 2000)
    fun console(){
        BindingPage().run()
    }


}

//fun main() {
//    Spider(ServiceRepository(ApplicationRepository())).spider()
//}