package xapo.saeed.xapo_test.mvp.repo_list;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import xapo.saeed.xapo_test.api.request.GitHubAPI;
import xapo.saeed.xapo_test.api.request.GitHubAPIAdapter;
import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;
import xapo.saeed.xapo_test.model.QueryParam;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoListModel implements RepoListContract.Repo {

    @Override
    public void getRepo(@NonNull QueryParam queryParam, @NonNull final RepoListContract.ResponseCallback callback) {
        GitHubAPI api = GitHubAPIAdapter.createGitHubAPIAdapter();
        api.getTrendingRepos(ANDROID_QUERY_STRING, queryParam.getSortBy(), queryParam.getOrderBy(), queryParam.getPerPage(), queryParam.getPage())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GitHubRepoResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GitHubRepoResponse repoResponse) {
                        callback.success(repoResponse.getItems());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.error(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
