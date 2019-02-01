package xyz.nietongxue.serviceql

import com.coxautodev.graphql.tools.GraphQLResolver
import xyz.nietongxue.serviceql.Author
import xyz.nietongxue.serviceql.Book


class BookResolver(private val authorRepository: AuthorRepository) : GraphQLResolver<Book> /* This class is a resolver for the xyz.nietongxue.serviceql.Book "Data Class" */ {

    fun author(book: Book): Author {
        return authorRepository.findById(book.authorId)
    }
}