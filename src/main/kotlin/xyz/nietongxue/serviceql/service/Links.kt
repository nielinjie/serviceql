package xyz.nietongxue.serviceql.service

import com.coxautodev.graphql.tools.GraphQLResolver
import org.springframework.stereotype.Component
import java.util.*

@Component
class LinkResolver(val serviceRepository: ServiceRepository) : GraphQLResolver<Link> {
    fun service(link: Link) = serviceRepository.getById(link.serviceId)
}

@Component
class LinkRepository(val applicationRepository: ApplicationRepository) {
    private val lks: MutableSet<Link> = mutableSetOf(
//            Link(" a to b", "service.a", applicationRepository.b())
    )

    fun all() = lks.toList()
    fun add(links: List<Link>) {
        lks.addAll(links)
    }
}


data class Link(val id: String, val serviceId: String, val consumer: Application)


@Component
class LinkInspector(val linkRepository: LinkRepository, val serviceRepository: ServiceRepository) : Inspector {
    override fun inspect(): List<Issue> {
        return linkRepository.all().filter {
            serviceRepository.getById(it.serviceId)?.provider == null
        }.map {
            Issue(
                    UUID.randomUUID().toString(), "no provider link -  service=${it.serviceId}", 5, it.id
            )
        }
    }

}
