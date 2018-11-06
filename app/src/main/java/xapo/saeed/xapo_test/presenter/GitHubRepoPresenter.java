package xapo.saeed.xapo_test.presenter;

import androidx.annotation.NonNull;
import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;
import xapo.saeed.xapo_test.model.GitHubRepoModel;
import xapo.saeed.xapo_test.ui.MainView;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoPresenter implements Presenter {

    private GitHubRepoModel model;
    private MainView view;

    public GitHubRepoPresenter(@NonNull GitHubRepoModel model, @NonNull MainView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getRepo(@NonNull String sort, String orderBy, String perPage) {
        view.showProgressDialog();
        model.getAndroidRepo(sort, orderBy, perPage);
    }

    @Override
    public void onError(Throwable throwable) {
        view.hideProgressDialog();
        view.onError(throwable);
    }

    @Override
    public void success(@NonNull GitHubRepoResponse response) {
        view.hideProgressDialog();
        view.onSuccess(response);
    }
}