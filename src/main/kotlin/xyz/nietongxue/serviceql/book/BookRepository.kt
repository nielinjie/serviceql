package xyz.nietongxue.serviceql.book

import org.springframework.stereotype.Component

import java.util.ArrayList


@Component
class BookRepository {
    fun findAll(): List<Book> {
        val books = ArrayList<Book>()
        books.add(Book(1, "Jason's book", 1))
        return books
    }
}
