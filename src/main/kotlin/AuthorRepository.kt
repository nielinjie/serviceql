
package xyz.nietongxue.serviceql

import org.springframework.stereotype.Component
import xyz.nietongxue.serviceql.Author

@Component
class AuthorRepository {
    fun findById(authorId: Int): Author {
        return Author(1, "Jason")
    }

    fun findAll(): List<Author> {
        return listOf(findById(1))
    }
}
