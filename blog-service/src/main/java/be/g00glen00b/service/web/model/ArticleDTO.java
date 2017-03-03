package be.g00glen00b.service.web.model;

import java.time.LocalDateTime;

public class ArticleDTO {
    private Long id;
    private String title;
    private String text;
    private String slug;
    private LocalDateTime created;

    public ArticleDTO(Long id, String title, String text, String slug, LocalDateTime created) {
        this.id = id;
        this.title = title;
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
}
