package main.java.org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class MyFitPalApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyFitPalApplication.class, args);
    }
}