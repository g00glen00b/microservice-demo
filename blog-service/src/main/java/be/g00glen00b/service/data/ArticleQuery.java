package be.g00glen00b.service.data;

public class ArticleQuery {
    private String search;
    private Long user;

    public ArticleQuery(String search, Long user) {
        this.search = search;
        this.user = user;
    }

    public String getSearch() {
        return search;
    }

    public Long getUser() {
        return user;
    }
}
