package be.g00glen00b.service.security.model;

import java.util.Set;

public class UserObject {
    private String username;
    private boolean enabled;
    private String token;
    private Set<AuthorityObject> authorities;

    public UserObject() {
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public Set<AuthorityObject> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Set<AuthorityObject> authorities) {
        this.authorities = authorities;
    }
}
