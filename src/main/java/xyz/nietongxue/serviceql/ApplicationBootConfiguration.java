package xyz.nietongxue.serviceql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:java.lang.RuntimeException@gmail.com">oEmbedler Inc.</a>
 */
@SpringBootApplication
public class ApplicationBootConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootConfiguration.class, args);
    }

//    @Bean
//    GraphQLSchema schema() {
//         return SchemaParser.newParser()
//                .file("book.graphqls")
////                 .resolvers(new RootResolver(new BookRepository()),new BookResolver(new AuthorRepository()))
//                 .build().makeExecutableSchema();
////        return GraphQLSchema.newSchema()
//                .query(GraphQLObjectType.newObject()
//                        .name("query")
//                        .field(field -> field
//                                .name("test")
//                                .type(Scalars.GraphQLString)
//                                .dataFetcher(environment -> "response")
//                        )
//                        .build())
//                .build();
//    }
}
