package xapo.saeed.xapo_test.ui;

import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;

/**
 * Created on 06/11/2018.
 */
public interface MainView {

    void onError(Throwable throwable);

    void onSuccess(GitHubRepoResponse repoResponse);

    void showProgressDialog();

    void hideProgressDialog();

}