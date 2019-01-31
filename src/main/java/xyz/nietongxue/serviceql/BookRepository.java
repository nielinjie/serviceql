package xyz.nietongxue.serviceql;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Component
public class BookRepository {
    public List<Book> findAll() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book(1, "Jason's book", 1));
        return books;
    }
}
