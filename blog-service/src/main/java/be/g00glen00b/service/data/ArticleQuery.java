package be.g00glen00b.service.data;

public class ArticleQuery {
    private String search;
    private String username;

    public ArticleQuery(String search, String username) {
        this.search = search;
        this.username = username;
    }

    public String getSearch() {
        return search;
    }

    public String getUsername() {
        return username;
    }
}
