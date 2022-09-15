package projectJDBC;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import projectJDBC.service.GetDateService;
import projectJDBC.service.WebService;

import java.sql.SQLException;


@EnableMongoRepositories
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Main {
    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(Main.class);
        WebService webService = context.getBean(WebService.class);
//        webService.deleteBookById("ada");



        //Console.main(args);
    }


}
