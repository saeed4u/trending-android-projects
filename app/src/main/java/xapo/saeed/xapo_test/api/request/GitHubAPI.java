package xapo.saeed.xapo_test.api.request;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xapo.saeed.xapo_test.api.response.GitHubRepoOwner;
import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;

/**
 * Created on 06/11/2018.
 */
public interface GitHubAPI {

    //Search API
    @GET("search/repositories")
    Observable<GitHubRepoResponse> getTrendingRepos(@Query("q") String query, @Query("b") String sortBy,
                                                    @Query("order") String orderBy, @Query("per_page") int perPage, @Query("page") int page);

    @GET("repos/{owner}/{name}/contributors")
    Observable<List<GitHubRepoOwner>> getContributors(@Path("owner") String owner, @Path("name") String name);
}
