package xapo.saeed.xapo_test.model;

import androidx.annotation.NonNull;

/**
 * Created on 09/11/2018.
 */
public class QueryParam {

    private String sortBy;
    private String orderBy = "DESC";
    private int perPage = 10;
    private int page = 1;

    public QueryParam() {
    }

    public QueryParam(String sortBy, String orderBy, int perPage, int page) {
        this.sortBy = sortBy;
        this.orderBy = orderBy;
        this.perPage = perPage;
        this.page = page;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(@NonNull String sortBy) {
        this.sortBy = sortBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
