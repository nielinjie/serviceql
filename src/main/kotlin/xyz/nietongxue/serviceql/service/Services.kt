package xyz.nietongxue.serviceql.service

import org.springframework.stereotype.Component

@Component
class ServiceRepository(val applicationRepository: ApplicationRepository
                        , val linkRepository: LinkRepository) {
    private val services = mutableListOf<Service>(
//            Service("service.a", "the service", "1.0.0", ServiceType.Dubbo, applicationRepository.a())
    )

    fun all() = services
    fun getById(id: String) = services.find { it.id == id }
    fun addFromDubbo(serviceString: String, providers: List<String>, consumers: List<String>) {
        val parts = serviceString.split(":")
        val service = Service(
                serviceString, parts.first(), parts.getOrElse(1) { "" }
                , ServiceType.Dubbo, providers.first()?.let {
            applicationRepository.getByName(it) ?: Application(it, it).also {
                applicationRepository.add(it)
            }
        }
        )
        val links = consumers.map {
            applicationRepository.getByName(it) ?: Application(it, it).also {
                applicationRepository.add(it)
            }
        }.map {
            Link(serviceString + it.name, service.id, it)
        }
        services.add(service)
        linkRepository.add(links)
    }
}

data class Service(val id: String, val name: String, val version: String, val type: ServiceType, val provider: Application?)