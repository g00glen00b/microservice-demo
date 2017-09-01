package be.g00glen00b.service;

import be.g00glen00b.service.model.Registration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(Registration.class)
public class RegistrationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationServiceApplication.class, args);
    }

    @ConditionalOnProperty("mail.configuration")
    @PropertySource(value = "file:${mail.configuration}", ignoreResourceNotFound = true)
    public static class RegistrationServiceSecretConfiguration {

    }
}
