package be.g00glen00b.service.data;

import java.sql.Blob;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profile_avatar")
public class ProfileAvatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @Lob
    private Blob avatar;
    private String mime;

    public ProfileAvatar(Long id, Profile profile, Blob avatar, String mime) {
        this.id = id;
        this.profile = profile;
        this.avatar = avatar;
        this.mime = mime;
    }

    public ProfileAvatar(Profile profile, Blob avatar, String mime) {
        this.profile = profile;
        this.avatar = avatar;
        this.mime = mime;
    }

    public ProfileAvatar(Profile profile) {
        this.profile = profile;
    }

    public ProfileAvatar() {
    }

    public Long getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Blob getAvatar() {
        return avatar;
    }
    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public String getMime() {
        return mime;
    }
    public void setMime(String mime) {
        this.mime = mime;
    }
}
