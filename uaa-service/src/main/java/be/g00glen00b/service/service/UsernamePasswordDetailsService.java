package be.g00glen00b.service.service;

import java.util.List;
import java.util.stream.Collectors;
import be.g00glen00b.service.data.Role;
import be.g00glen00b.service.data.User;
import be.g00glen00b.service.data.UserRepository;
import be.g00glen00b.service.model.TokenUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsernamePasswordDetailsService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private UserService userService;
    private TokenService tokenService;

    @Autowired
    public UsernamePasswordDetailsService(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Trying to authenticate ", username);
        try {
            return getUserDetails(userService.findByUsername(username));
        } catch (UserNotFoundException ex) {
            throw new UsernameNotFoundException("Username '" + username + "' not found", ex);
        }
    }

    private TokenUserDetails getUserDetails(User user) {
        return new TokenUserDetails(
            user.getEmail(),
            user.getPassword(),
            tokenService.encode(user),
            user.isEnabled(),
            getAuthorities(user.getRoles()));
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        return roles
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getRole()))
            .collect(Collectors.toList());
    }
}
