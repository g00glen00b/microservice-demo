package be.g00glen00b.service.data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@EntityListeners(ProfileRegistrationListener.class)
public class Profile {
    @Id
    private String username;
    private String firstname;
    private String lastname;
    private String bio;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProfileAvatar avatar;

    public Profile(String username, String firstname, String lastname, String bio, ProfileAvatar avatar) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bio = bio;
        this.avatar = avatar;
    }

    public Profile() {
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    public ProfileAvatar getAvatar() {
        return avatar;
    }
    public void setAvatar(ProfileAvatar avatar) {
        this.avatar = avatar;
    }
}
