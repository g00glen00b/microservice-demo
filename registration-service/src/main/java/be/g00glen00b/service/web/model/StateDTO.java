package be.g00glen00b.service.web.model;

import java.time.LocalDateTime;
import be.g00glen00b.service.data.State;

public class StateDTO {
    private Long id;
    private String email;
    private String username;
    private LocalDateTime created;

    public StateDTO(Long id, String email, String username, LocalDateTime created) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.created = created;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public static StateDTO fromEntity(State entity) {
        return new StateDTO(entity.getId(), entity.getEmail(), entity.getUsername(), entity.getCreated());
    }
}
