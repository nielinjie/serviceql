package xyz.nietongxue.serviceql.service

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class QueryResolver(
        val serviceRepository: ServiceRepository,
        val linkRepository: LinkRepository,
        val issueRepository: IssueRepository
) : GraphQLQueryResolver {
    fun services(): List<Service> = serviceRepository.all()
    fun links():List<Link> = linkRepository.all()
    fun issues():List<Issue> = issueRepository.all()
}


