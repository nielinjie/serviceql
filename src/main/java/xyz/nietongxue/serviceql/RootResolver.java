package xyz.nietongxue.serviceql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;


public class RootResolver implements GraphQLQueryResolver {

    private BookRepository bookRepository;

    public RootResolver(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> books() {
        return bookRepository.findAll();
    }
}