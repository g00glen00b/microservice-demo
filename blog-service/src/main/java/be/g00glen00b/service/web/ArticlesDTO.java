package be.g00glen00b.service.web;

import java.util.List;

public class ArticlesDTO {
    private int offset;
    private int limit;
    private long totalResults;
    private List<ArticleDTO> results;

    public ArticlesDTO(int offset, int limit, long totalResults, List<ArticleDTO> results) {
        this.offset = offset;
        this.limit = limit;
        this.totalResults = totalResults;
        this.results = results;
    }

    public ArticlesDTO() {
    }

    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticleDTO> getResults() {
        return results;
    }
    public void setResults(List<ArticleDTO> results) {
        this.results = results;
    }
}
