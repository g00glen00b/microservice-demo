package be.g00glen00b.service.security.service;

import be.g00glen00b.service.security.model.TokenProperties;
import be.g00glen00b.service.security.model.UserObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {
    private RestTemplate restTemplate;
    private TokenProperties properties;

    @Autowired
    public TokenService(@Qualifier("restTemplate") RestTemplate restTemplate, TokenProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    UserObject decode(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(properties.getHeader(), token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(properties.getEndpoint(), HttpMethod.GET, entity, UserObject.class).getBody();
    }
}
