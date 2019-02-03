package xyz.nietongxue.serviceql.service

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class QueryResolver(
        val serviceRepository: ServiceRepository,
        val linkRepository: LinkRepository
) : GraphQLQueryResolver {
    fun services(): List<Service> = serviceRepository.all().toList()
    fun links():List<Link> = linkRepository.all().toList()
}


