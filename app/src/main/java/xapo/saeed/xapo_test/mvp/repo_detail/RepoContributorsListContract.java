package xapo.saeed.xapo_test.mvp.repo_detail;

import java.util.List;

import androidx.annotation.NonNull;
import xapo.saeed.xapo_test.api.response.GitHubRepoOwner;

/**
 * Created on 12/11/2018.
 */
public interface RepoContributorsListContract {


    interface Presenter {
        void getContributors(@NonNull String repoFullName);
    }


    interface ResponseCallback {
        void success(@NonNull List<GitHubRepoOwner> contributors);

        void error(Throwable throwable);
    }


    interface Repo {
        void getContributors(@NonNull String owner, @NonNull String name, @NonNull RepoContributorsListContract.ResponseCallback callback);
    }


    interface View {
        void showLoader();

        void hideLoader();

        void success(@NonNull List<GitHubRepoOwner> contributors);

        void error(Throwable throwable);
    }

}
