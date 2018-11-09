package xapo.saeed.xapo_test.mvp.repo_list;

import java.util.List;

import androidx.annotation.NonNull;
import xapo.saeed.xapo_test.api.response.GitHubRepo;
import xapo.saeed.xapo_test.model.QueryParam;

/**
 * Created on 09/11/2018.
 */
public interface RepoListContract {

    interface Presenter {
        void getRepo(@NonNull QueryParam queryParam);
    }


    interface ResponseCallback {
        void success(@NonNull List<GitHubRepo> repoList);

        void error(Throwable throwable);
    }


    interface Repo {
        String ANDROID_QUERY_STRING = "android language:java";
        void getRepo(@NonNull QueryParam queryParam, @NonNull ResponseCallback callback);
    }


    interface View {
        void showLoader();

        void hideLoader();

        void success(@NonNull List<GitHubRepo> repos);

        void error(Throwable throwable);
    }


}
