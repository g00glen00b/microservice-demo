package be.g00glen00b.service.service;

import be.g00glen00b.service.model.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    private RestTemplate restTemplate;

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProfileDTO register(String username, String password) {
        return null;
    }

    private String saveAuthentication(String username, String password) {
        return null;
    }

    private String saveProfile(String username, String token) {
        return null;
    }
}
