package xapo.saeed.xapo_test.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;
import xapo.saeed.xapo_test.model.GitHubRepoModel;
import xapo.saeed.xapo_test.model.Model;
import xapo.saeed.xapo_test.ui.MainView;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoPresenter implements Presenter {

    private Model model;
    private MainView view;

    public GitHubRepoPresenter(@NonNull MainView view) {
        this.view = view;
        this.model = new GitHubRepoModel(this, view.getCurrentContext());
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
        Log.v("Called", "Size = " + response.getItems().size());
        if (view == null) {
            Log.v("Well", "Well");
        }
        view.onSuccess(response);
    }
}
