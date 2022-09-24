package projectJDBC;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import projectJDBC.service.GetDateService;

import projectJDBC.service.WebService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;



@SpringBootApplication
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ApplicationContext context = SpringApplication.run(Main.class);

    }


}
