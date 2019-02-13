package xyz.nietongxue.serviceql.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


interface Inspector {
    fun inspect(): List<Issue>
}


@Component
class Inspec(val issueRepository: IssueRepository) {


    @Autowired
    var inspectors: List<Inspector> = emptyList()


    @Scheduled(fixedDelay = 1000L * 60L * 60L, //1hour
            initialDelay = 1000L * 60L * 5L  //5min
    )
    fun inspect() {
        inspectors.forEach {
            issueRepository.addAll(it.inspect())
        }
    }
}