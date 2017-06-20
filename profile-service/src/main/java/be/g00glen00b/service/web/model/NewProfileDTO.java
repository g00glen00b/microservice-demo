package be.g00glen00b.service.web.model;

public class NewProfileDTO extends ProfileDTO {
    private String email;

    public NewProfileDTO(String email, String username, String firstname, String lastname, String bio) {
        super(username, firstname, lastname, bio);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
