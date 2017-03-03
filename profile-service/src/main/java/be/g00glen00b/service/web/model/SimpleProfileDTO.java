package be.g00glen00b.service.web.model;

import be.g00glen00b.service.data.Profile;

public class SimpleProfileDTO {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;

    public SimpleProfileDTO(Long id, String username, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public SimpleProfileDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public static SimpleProfileDTO fromEntity(Profile entity) {
        return new SimpleProfileDTO(entity.getId(), entity.getUsername(), entity.getFirstname(), entity.getLastname());
    }
}
