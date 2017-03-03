package be.g00glen00b.service.web.config;

import be.g00glen00b.service.web.TokenHeaderInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {
    @Bean
    @Primary
    @LoadBalanced
    public RestTemplate securedRestTemplate(TokenHeaderInterceptor interceptor) {
        return new RestTemplateBuilder()
            .additionalInterceptors(interceptor)
            .build();
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
