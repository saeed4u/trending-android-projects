package xapo.saeed.xapo_test.model;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;
import xapo.saeed.xapo_test.R;
import xapo.saeed.xapo_test.api.request.GitHubAPI;
import xapo.saeed.xapo_test.api.request.GitHubAPIAdapter;
import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;
import xapo.saeed.xapo_test.presenter.Presenter;
import xapo.saeed.xapo_test.util.NetworkNotAvailableException;
import xapo.saeed.xapo_test.util.NetworkUtils;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoModel implements Model {

    private Presenter presenter;
    private Context context;
    private PublishProcessor<Integer> paginator = PublishProcessor.create();

    public GitHubRepoModel(@NonNull Presenter presenter, @NonNull Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void getAndroidRepo(@NonNull final String sort, String orderBy, int perPage, int page) {
        if (TextUtils.isEmpty(orderBy)) {
            orderBy = "desc";
        }

        if (perPage == 0) {
            perPage = 10;
        }
        if (page == 0){
            page = 1;
        }
        final String finalOrderBy = orderBy;
        final int finalPerPage = perPage;
        final int finalPage = page;
        NetworkUtils.networkAvailable(context)
                .flatMap(new Function<Boolean, ObservableSource<GitHubRepoResponse>>() {
                    @Override
                    public ObservableSource<GitHubRepoResponse> apply(Boolean aBoolean) throws Exception {
                        if (!aBoolean) {
                            throw new NetworkNotAvailableException(context.getString(R.string.error_no_internet));
                        }
                        GitHubAPI api = GitHubAPIAdapter.createGitHubAPIAdapter();
                        return api.getTrendingRepos(ANDROID_QUERY_STRING, sort, finalOrderBy, finalPerPage, finalPage);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GitHubRepoResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GitHubRepoResponse repoResponse) {
                presenter.success(repoResponse);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                presenter.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
