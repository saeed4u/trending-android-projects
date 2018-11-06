package xapo.saeed.xapo_test.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import xapo.saeed.xapo_test.R;
import xapo.saeed.xapo_test.adapter.GitHubRepoAdapter;
import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;
import xapo.saeed.xapo_test.presenter.GitHubRepoPresenter;
import xapo.saeed.xapo_test.presenter.Presenter;
import xapo.saeed.xapo_test.ui.MainView;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoListFragment extends BaseFragment implements MainView {

    private static final String SORT_BY = "stars";
    private static final String ORDER_BY = "desc";
    private static final int PER_PAGE = 10;
    private static final String FETCHED_REPOS = "fetched_repos";

    @BindView(R.id.android_repos)
    RecyclerView android_repos;

    private GitHubRepoResponse repoResponse;
    private GitHubRepoAdapter repoAdapter;
    private Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new GitHubRepoPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ButterKnife.bind(this, view);
        if (savedInstanceState != null) {
            repoResponse = savedInstanceState.getParcelable(FETCHED_REPOS);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (repoResponse != null && !repoResponse.getItems().isEmpty()) {
            repoAdapter = new GitHubRepoAdapter(repoResponse.getItems());
            android_repos.setAdapter(repoAdapter);
        } else {
            presenter.getRepo(SORT_BY, ORDER_BY, PER_PAGE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (repoResponse != null) {
            outState.putParcelable(FETCHED_REPOS, repoResponse);
        }
    }


    @Override
    public void onError(Throwable throwable) {
        handleError(throwable);
    }

    @Override
    public void onSuccess(@NotNull GitHubRepoResponse repoResponse) {
        this.repoResponse = repoResponse;
        repoAdapter = new GitHubRepoAdapter(this.repoResponse.getItems());
        Log.v("Called", "repoResponse = ");
        android_repos.setAdapter(repoAdapter);
        if (android_repos == null){
        }
    }

    @Override
    public void showProgressDialog() {
        showLoader(R.string.a_moment_please);
    }

    @Override
    public void hideProgressDialog() {
        dismissDialog();
    }

    @NonNull
    @Override
    public Context getCurrentContext() {
        return activity;
    }
}
