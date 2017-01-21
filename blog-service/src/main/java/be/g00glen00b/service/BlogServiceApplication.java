package be.g00glen00b.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BlogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogServiceApplication.class, args);
    }
}
