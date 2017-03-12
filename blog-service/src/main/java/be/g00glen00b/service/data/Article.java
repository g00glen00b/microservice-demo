package be.g00glen00b.service.data;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private String username;
    private String slug;
    private LocalDateTime created;

    public Article(Long id, String title, String text, String username, String slug, LocalDateTime created) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.username = username;
        this.slug = slug;
        this.created = created;
    }

    public Article(String title, String text, String username, String slug, LocalDateTime created) {
        this(null, title, text, username, slug, created);
    }

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
