package xyz.nietongxue.serviceql.service

import org.springframework.stereotype.Component

@Component
class IssueRepository(val serviceRepository: ServiceRepository) {
    private val issues: MutableList<Issue> = mutableListOf()
    fun add(issue: Issue) {
        issues.add(issue)
    }

    fun all() = issues.toList()
    fun addAll(issues: List<Issue>) {
        this.issues.addAll(issues)
    }

    fun clear() {
        this.issues.clear()
    }
}

data class Issue(val id: String, val message: String, val level: Int, val linkId: String?)