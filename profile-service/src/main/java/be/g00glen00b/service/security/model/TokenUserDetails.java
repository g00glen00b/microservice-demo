package be.g00glen00b.service.security.model;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class TokenUserDetails extends User {
    private String token;

    public TokenUserDetails(String username, String password, String token, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, true, true, true, authorities);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public static TokenUserDetails fromUserObject(UserObject object) {
        return new TokenUserDetails(object.getUsername(), "", object.getToken(), object.isEnabled(), object.getAuthorities().stream()
            .map(AuthorityObject::getAuthority)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList()));
    }
}
