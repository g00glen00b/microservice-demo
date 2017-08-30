package be.g00glen00b.service.data;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EntityListeners(StateUpdateListener.class)
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private boolean profile;
    private boolean uaa;
    private LocalDateTime created;

    public State() {
    }

    public State(Long id, String email, String username, boolean profile, boolean uaa, LocalDateTime created) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.profile = profile;
        this.uaa = uaa;
        this.created = created;
    }

    public State(String email, String username, boolean profile, boolean uaa, LocalDateTime created) {
        this(null, email, username, profile, uaa, created);
    }

    public State(String email, String username, LocalDateTime created) {
        this(email, username, false, false, created);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public boolean isProfile() {
        return profile;
    }
    public void setProfile(boolean profile) {
        this.profile = profile;
    }

    public boolean isUaa() {
        return uaa;
    }
    public void setUaa(boolean uaa) {
        this.uaa = uaa;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
