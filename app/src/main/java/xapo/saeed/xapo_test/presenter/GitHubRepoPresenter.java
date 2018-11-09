package xapo.saeed.xapo_test.presenter;

import java.util.List;

import androidx.annotation.NonNull;
import xapo.saeed.xapo_test.api.response.GitHubRepo;
import xapo.saeed.xapo_test.model.QueryParam;
import xapo.saeed.xapo_test.mvp.repo_list.RepoListContract;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoPresenter implements RepoListContract.Presenter {

    private RepoListContract.Repo repo;
    private RepoListContract.View view;

    public GitHubRepoPresenter(@NonNull RepoListContract.View view, @NonNull RepoListContract.Repo repo) {
        this.view = view;
        this.repo = repo;
    }

    private RepoListContract.ResponseCallback callback = new RepoListContract.ResponseCallback() {
        @Override
        public void success(@NonNull List<GitHubRepo> repoList) {
            view.hideLoader();
            view.success(repoList);
        }

        @Override
        public void error(Throwable throwable) {
            view.error(throwable);
            view.hideLoader();
        }
    };

    @Override
    public void getRepo(@NonNull QueryParam queryParam) {
        view.showLoader();
        repo.getRepo(queryParam, callback);
    }
}
