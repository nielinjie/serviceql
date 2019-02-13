package xyz.nietongxue.serviceql.service

import org.springframework.stereotype.Component

@Component
class ServiceRepository(val applicationRepository: ApplicationRepository
                        , val linkRepository: LinkRepository) {
    private val services = mutableMapOf<String, Service>(
//            Service("service.a", "the service", "1.0.0", ServiceType.Dubbo, applicationRepository.a())
    )

    fun all() = services.values.toList()
    fun getById(id: String) = services.get(id)
    fun addFromDubbo(serviceString: String, providers: List<String>, consumers: List<String>) {
        val parts = serviceString.split(":")
        val service = Service(
                serviceString, parts.first(), parts.getOrElse(1) { "" }
                , ServiceType.Dubbo, providers.firstOrNull()?.let {
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
        services.put(service.id, service)
        linkRepository.add(links)
    }

    fun addFromRabbit(source: Name, destination: Name) {
        val service = Service(source.string, source.string, "", ServiceType.Event,
                applicationRepository.getByName(source.nomarlized.first())
                        ?: Application(source.nomarlized.first(), source.nomarlized.first()).also {
                            applicationRepository.add(it)
                        }
        )
        val link =
                (applicationRepository.getByName(destination.nomarlized.first())
                        ?: Application(destination.nomarlized.first(), destination.nomarlized.first()).also {
                            applicationRepository.add(it)
                        }).let {
                    Link(source.string + it.name, service.id, it)
                }
        services.put(service.id, service)
        linkRepository.add(listOf(link))
    }
}

data class Service(val id: String, val name: String, val version: String, val type: ServiceType, val provider: Application?)