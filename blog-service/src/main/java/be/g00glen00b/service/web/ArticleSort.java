package be.g00glen00b.service.web;

public enum ArticleSort {
    CREATED_ASC("created", true), CREATED_DESC("created", false);

    private String field;
    private boolean ascending;

    ArticleSort(String field, boolean ascending) {
        this.field = field;
        this.ascending = ascending;
    }

    public String getField() {
        return field;
    }

    public boolean isAscending() {
        return ascending;
    }

    public String getSortString() {
        return (isAscending() ? "+" : "-") + field;
    }
}
