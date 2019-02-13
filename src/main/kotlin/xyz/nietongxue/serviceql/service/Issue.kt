package xyz.nietongxue.serviceql.service

import org.springframework.stereotype.Component

@Component
class IssueRepository {
    private val issues: MutableSet<Issue> = mutableSetOf()
    fun add(issue: Issue) {
        issues.add(issue)
    }

    fun all() = issues.toList()
    fun addAll(issues: List<Issue>) {
        this.issues.addAll(issues)
    }


}

data class Issue(val id: String, val message: String, val level: Int, val linkId: String?)