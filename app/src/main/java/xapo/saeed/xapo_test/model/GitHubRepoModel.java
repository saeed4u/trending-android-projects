package xapo.saeed.xapo_test.model;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import xapo.saeed.xapo_test.api.request.GitHubAPI;
import xapo.saeed.xapo_test.api.request.GitHubAPIAdapter;
import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;
import xapo.saeed.xapo_test.presenter.Presenter;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoModel implements Model {

    private Presenter presenter;


    @Override
    public void getAndroidRepo(@NonNull String sort, String orderBy, int perPage) {
        if (TextUtils.isEmpty(orderBy)) {
            orderBy = "desc";
        }

        if (perPage == 0) {
            perPage = 10;
        }
        GitHubAPI api = GitHubAPIAdapter.createGitHubAPIAdapter();
        api.getTrendingRepos(ANDROID_QUERY_STRING, sort, orderBy, perPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GitHubRepoResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GitHubRepoResponse repoResponse) {
                        if (presenter != null) {
                            presenter.success(repoResponse);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (presenter != null) {
                            presenter.onError(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
