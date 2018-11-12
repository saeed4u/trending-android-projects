package xapo.saeed.xapo_test.mvp.repo_detail;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import xapo.saeed.xapo_test.api.request.GitHubAPI;
import xapo.saeed.xapo_test.api.request.GitHubAPIAdapter;
import xapo.saeed.xapo_test.api.response.GitHubRepoOwner;

/**
 * Created on 12/11/2018.
 */
public class GitHubRepoContributorsModel implements RepoContributorsListContract.Repo {


    @Override
    public void getContributors(@NonNull String owner,@NonNull String name, @NonNull final RepoContributorsListContract.ResponseCallback callback) {
        GitHubAPI api = GitHubAPIAdapter.createGitHubAPIAdapter();
        api.getContributors(owner,name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GitHubRepoOwner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<GitHubRepoOwner> gitHubRepoOwner) {
                        callback.success(gitHubRepoOwner);
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
