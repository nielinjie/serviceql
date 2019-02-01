package xyz.nietongxue.serviceql.book

import com.coxautodev.graphql.tools.GraphQLResolver
import org.springframework.stereotype.Component

@Component
class BookResolver(private val authorRepository: AuthorRepository) : GraphQLResolver<Book> /* This class is a resolver for the xyz.nietongxue.serviceql.book.Book "Data Class" */ {

    fun author(book: Book): Author {
        return authorRepository.findById(book.authorId)
    }
}