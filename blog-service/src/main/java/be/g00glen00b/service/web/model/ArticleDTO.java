package be.g00glen00b.service.web.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import be.g00glen00b.service.data.Article;

public class ArticleDTO {
    private Long id;
    @NotNull
    @Size(max = 64)
    private String title;
    @Size(max = 16384)
    private String text;
    private String username;
    @NotNull
    @Size(max = 64)
    private String slug;
    private LocalDateTime created;

    public ArticleDTO(Long id, String title, String username, String text, String slug, LocalDateTime created) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.text = text;
        this.slug = slug;
        this.created = created;
    }

    public ArticleDTO() {
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

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
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
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public static ArticleDTO fromEntity(Article entity) {
        return new ArticleDTO(entity.getId(), entity.getTitle(), entity.getUsername(), entity.getText(), entity.getSlug(), entity.getCreated());
    }
}
