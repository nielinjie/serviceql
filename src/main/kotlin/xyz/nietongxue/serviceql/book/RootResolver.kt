package xyz.nietongxue.serviceql.book


import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class RootResolver(private val bookRepository: BookRepository, private  val authorRepository: AuthorRepository) : GraphQLQueryResolver {

    fun books(): List<Book> {
        return bookRepository.findAll()
    }
    fun authors() = authorRepository.findAll()
}