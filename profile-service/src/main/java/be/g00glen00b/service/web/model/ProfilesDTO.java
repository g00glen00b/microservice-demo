package be.g00glen00b.service.web.model;

import java.util.List;

public class ProfilesDTO {
    private int offset;
    private int limit;
    private long totalResults;
    private List<SimpleProfileDTO> results;

    public ProfilesDTO(int offset, int limit, long totalResults, List<SimpleProfileDTO> results) {
        this.offset = offset;
        this.limit = limit;
        this.totalResults = totalResults;
        this.results = results;
    }

    public ProfilesDTO() {
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public List<SimpleProfileDTO> getResults() {
        return results;
    }
}
