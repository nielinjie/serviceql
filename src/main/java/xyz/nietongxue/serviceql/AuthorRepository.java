package xyz.nietongxue.serviceql;


import org.springframework.stereotype.Component;

@Component
public class AuthorRepository {
    public Author findById(int authorId) {
        return new Author(1,"Jason");
    }
}
