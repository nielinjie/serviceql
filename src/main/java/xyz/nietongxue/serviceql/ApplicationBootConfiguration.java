package xyz.nietongxue.serviceql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApplicationBootConfiguration {
    public static ApplicationContext context;
    public static void main(String[] args) {

        ApplicationBootConfiguration.context = SpringApplication.run(ApplicationBootConfiguration.class, args);

    }
}
