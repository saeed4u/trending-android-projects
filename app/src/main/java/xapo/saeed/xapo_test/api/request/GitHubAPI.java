package xapo.saeed.xapo_test.api.request;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;

/**
 * Created on 06/11/2018.
 */
public interface GitHubAPI {

    //Search API
    @GET("search/repositories")
    Observable<GitHubRepoResponse> getTrendingRepos(@Query("q") String query, @Query("sort") String sortBy,
                                                    @Query("order") String orderBy, @Query("per_page") String perPage);

}