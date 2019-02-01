package xyz.nietongxue.serviceql


import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component
import xyz.nietongxue.serviceql.Book

@Component
class RootResolver(private val bookRepository: BookRepository,private  val authorRepository: AuthorRepository) : GraphQLQueryResolver {

    fun books(): List<Book> {
        return bookRepository.findAll()
    }
    fun authors() = authorRepository.findAll()
}