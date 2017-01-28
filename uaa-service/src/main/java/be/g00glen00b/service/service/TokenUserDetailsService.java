package be.g00glen00b.service.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import be.g00glen00b.service.data.Role;
import be.g00glen00b.service.data.User;
import be.g00glen00b.service.data.UserRepository;
import be.g00glen00b.service.model.TokenProperties;
import be.g00glen00b.service.model.TokenUserDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TokenUserDetailsService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private UserRepository repository;
    private String issuer;
    private TokenProperties properties;

    @Autowired
    public TokenUserDetailsService(UserRepository repository, TokenProperties properties, @Value("${spring.application.name}") String issuer) {
        this.repository = repository;
        this.issuer = issuer;
        this.properties = properties;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Trying to authenticate ", username);
        return repository.findByUsername(username)
            .map(this::getUserDetails)
            .orElseThrow(() -> new UsernameNotFoundException("Username '" + username + "' not found"));
    }

    private TokenUserDetails getUserDetails(User user) {
        return new TokenUserDetails(
            user.getUsername(),
            user.getPassword(),
            getToken(user),
            user.isEnabled(),
            getAuthorities(user.getRoles()));
    }

    private String getToken(User user) {
        LocalDateTime now = LocalDateTime.now();
        try {
            return JWT.create()
                .withIssuer(issuer)
                .withSubject(user.getUsername())
                .withIssuedAt(Date
                    .from(now.atZone(ZoneId.systemDefault())
                        .toInstant()))
                .withExpiresAt(Date
                    .from(now.plusSeconds(properties.getMaxAgeSeconds())
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .withArrayClaim("role", user
                    .getRoles()
                    .stream()
                    .map(Role::getRole)
                    .toArray(String[]::new))
                .sign(Algorithm.HMAC256(properties.getSecret()));
        } catch (JWTCreationException | UnsupportedEncodingException ex) {
            logger.error("Cannot properly create token", ex);
            throw new TokenCreationException("Cannot properly create token", ex);
        }
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        return roles
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getRole()))
            .collect(Collectors.toList());
    }
}
