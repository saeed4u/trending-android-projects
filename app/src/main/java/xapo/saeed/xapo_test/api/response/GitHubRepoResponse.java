package xapo.saeed.xapo_test.api.response;

import java.util.List;

import xapo.saeed.xapo_test.model.GitHubRepo;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoResponse {

    private long total_count;
    private boolean incomplete_results;

    private List<GitHubRepo> items;

    public long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(long total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<GitHubRepo> getItems() {
        return items;
    }

    public void setItems(List<GitHubRepo> items) {
        this.items = items;
    }
}
