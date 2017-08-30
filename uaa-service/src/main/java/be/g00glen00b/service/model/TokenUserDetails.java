package be.g00glen00b.service.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class TokenUserDetails extends User {
    private String token;
    private String profileName;

    public TokenUserDetails(String username, String profileName, String password, String token, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, true, true, true, authorities);
        this.profileName = profileName;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getProfileName() {
        return profileName;
    }
}
