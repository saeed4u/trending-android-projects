package xapo.saeed.xapo_test.presenter;

import androidx.annotation.NonNull;
import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;
import xapo.saeed.xapo_test.model.Model;
import xapo.saeed.xapo_test.ui.MainView;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoPresenter implements Presenter {

    private Model model;
    private MainView view;

    public GitHubRepoPresenter(@NonNull Model model, @NonNull MainView view) {
        this.model = model;
        this.view = view;
        this.model.setPresenter(this);
    }

    @Override
    public void getRepo(@NonNull String sort, String orderBy, int perPage) {
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
