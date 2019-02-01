package xyz.nietongxue.serviceql

import org.springframework.stereotype.Component
import xyz.nietongxue.serviceql.Book

import java.util.ArrayList


@Component
class BookRepository {
    fun findAll(): List<Book> {
        val books = ArrayList<Book>()
        books.add(Book(1, "Jason's book", 1))
        return books
    }
}
