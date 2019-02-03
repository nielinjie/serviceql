package xyz.nietongxue.serviceql.service

import com.coxautodev.graphql.tools.GraphQLResolver
import org.springframework.stereotype.Component

@Component
class LinkResolver(val serviceRepository: ServiceRepository) : GraphQLResolver<Link> {
    fun service(link: Link) = serviceRepository.getById(link.serviceId)
}

@Component
class LinkRepository(val applicationRepository: ApplicationRepository) {
    private val lks: MutableSet<Link> = mutableSetOf(
//            Link(" a to b", "service.a", applicationRepository.b())
    )

    fun all() = lks
    fun add(links: List<Link>) {
        lks.addAll(links)
    }
}

data class Link(val id: String, val serviceId: String, val consumer: Application)