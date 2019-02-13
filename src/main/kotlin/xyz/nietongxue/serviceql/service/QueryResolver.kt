package xyz.nietongxue.serviceql.service

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class QueryResolver(
        val serviceRepository: ServiceRepository,
        val linkRepository: LinkRepository,
        val issueRepository: IssueRepository,
        val applicationRepository: ApplicationRepository
) : GraphQLQueryResolver {
    fun services(): List<Service> = serviceRepository.all()
    fun links(): List<Link> = linkRepository.all()
    fun issues(): List<Issue> = issueRepository.all()
    fun applications(): List<Application> = applicationRepository.all()
    fun linksByApplication(provider: String?, consumer: String?): List<Link> {
        return linkRepository.all().filter { link ->
            val prov = (provider?.let {
                serviceRepository.getById(link.serviceId)?.provider?.name?.contains(it, true) ?: false
            }) ?: true
            val con = (consumer?.let { link.consumer.name.contains(it, true) }) ?: true
            prov && con
        }
    }
}


