package be.g00glen00b.service.model;

public class NewUser {
    private String email;
    private String username;
    private String password;

    public NewUser(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
