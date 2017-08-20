package be.g00glen00b.service.service;

import java.util.Collections;
import be.g00glen00b.service.data.Role;
import be.g00glen00b.service.data.User;
import be.g00glen00b.service.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    public static final String USER_ROLE = "user";
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public User findByUsername(String username) {
        return repository.findByEmail(username).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public String save(String email, String password) {
        if (repository.findByEmail(email).isPresent()) {
            throw new UsernameTakenException("Username is already taken");
        }
        Role role = new Role(email, USER_ROLE);
        User user = repository.saveAndFlush(new User(email, passwordEncoder.encode(password), true, Collections.singletonList(role)));
        return tokenService.encode(user);
    }
}
