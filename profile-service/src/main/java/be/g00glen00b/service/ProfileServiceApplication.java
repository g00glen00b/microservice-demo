package be.g00glen00b.service;

import be.g00glen00b.service.model.Registration;
import be.g00glen00b.service.security.model.TokenProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(Registration.class)
public class ProfileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileServiceApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix = "security.token")
    public TokenProperties properties() {
        return new TokenProperties();
    }
}
