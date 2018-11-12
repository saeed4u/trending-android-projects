package xapo.saeed.xapo_test.mvp.repo_detail;

import java.util.List;

import androidx.annotation.NonNull;
import xapo.saeed.xapo_test.api.response.GitHubRepoOwner;

/**
 * Created on 12/11/2018.
 */
public class GitHubRepoContributorsPresenter implements RepoContributorsListContract.Presenter {

    private RepoContributorsListContract.Repo repo;
    private RepoContributorsListContract.View view;

    public GitHubRepoContributorsPresenter(@NonNull RepoContributorsListContract.Repo repo, @NonNull RepoContributorsListContract.View view) {
        this.repo = repo;
        this.view = view;
    }

    private RepoContributorsListContract.ResponseCallback callback = new RepoContributorsListContract.ResponseCallback() {
        @Override
        public void success(@NonNull List<GitHubRepoOwner> contributors) {
            view.success(contributors);
            view.hideLoader();
        }

        @Override
        public void error(Throwable throwable) {
            view.hideLoader();
            view.error(throwable);
        }
    };

    @Override
    public void getContributors(@NonNull String repoFullName) {
        view.showLoader();
        String[] ownerAndName = repoFullName.split("/");
        repo.getContributors(ownerAndName[0], ownerAndName[1], callback);
    }
}
