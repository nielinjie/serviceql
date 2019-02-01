package xyz.nietongxue.serviceql.service

import org.springframework.stereotype.Component

@Component
class ServiceRepository(val applicationRepository: ApplicationRepository) {
    val the = Service("service.a", "the service", "1.0.0", ServiceType.Dubbo, applicationRepository.a())
    fun all() = listOf(the)
    fun getById(id:String) = the
}

data class Service(val id: String, val name: String, val version: String, val type: ServiceType, val provider: Application)