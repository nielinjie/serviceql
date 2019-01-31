package xyz.nietongxue.serviceql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;


public class BookResolver implements GraphQLResolver<Book> /* This class is a resolver for the xyz.nietongxue.serviceql.Book "Data Class" */ {

    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author author(Book book) {
        return authorRepository.findById(book.getAuthorId());
    }
}