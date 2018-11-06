package xapo.saeed.xapo_test.presenter;

import androidx.annotation.NonNull;
import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;

/**
 * Created on 06/11/2018.
 */
public interface Presenter {

    void getRepo(@NonNull String sort, String orderBy, String perPage);

    void onError(Throwable throwable);

    void success(@NonNull GitHubRepoResponse response);

}
