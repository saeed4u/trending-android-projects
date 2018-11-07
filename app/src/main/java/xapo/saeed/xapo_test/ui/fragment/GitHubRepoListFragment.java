package xapo.saeed.xapo_test.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import xapo.saeed.xapo_test.R;
import xapo.saeed.xapo_test.adapter.AdapterData;
import xapo.saeed.xapo_test.adapter.GitHubRepoAdapter;
import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;
import xapo.saeed.xapo_test.api.response.LoadingData;
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
    private static int PAGE = 1;
    private static final int VISIBLE_TRESHOLD = 1;

    //loading flag
    private boolean isLoading = false;
    private int totalItemCount;
    private int lastVisibleItemPosition;
    private LinearLayoutManager linearLayoutManager;

    @BindView(R.id.android_repos)
    RecyclerView android_repos;

    private ArrayList<AdapterData> repos;
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
            repos = savedInstanceState.getParcelableArrayList(FETCHED_REPOS);
        }
        linearLayoutManager = (LinearLayoutManager) android_repos.getLayoutManager();
        android_repos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //let's check if it's time to fetch more data
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (VISIBLE_TRESHOLD + lastVisibleItemPosition)) {
                    PAGE++;
                    isLoading = true;
                    repos.add(new LoadingData());
                    getAndroidRepos();
                }
            }
        });
        return view;
    }

    private void getAndroidRepos() {
        presenter.getRepo(SORT_BY, ORDER_BY, PER_PAGE, PAGE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (repos == null || repos.isEmpty()) {
            repos = new ArrayList<>();
            getAndroidRepos();
        }
        repoAdapter = new GitHubRepoAdapter(repos);
        android_repos.setAdapter(repoAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (repos != null) {
            outState.putParcelableArrayList(FETCHED_REPOS, repos);
        }
    }


    @Override
    public void onError(Throwable throwable) {
        handleError(throwable);
    }

    @Override
    public void onSuccess(@NotNull GitHubRepoResponse repoResponse) {
        repos.addAll(repoResponse.getItems());
        repoAdapter.notifyDataSetChanged();
        //reset loading flag
        if (isLoading) {
            isLoading = false;
        }
    }

    @Override
    public void showProgressDialog() {
        if (!isLoading) {
            showLoader(R.string.a_moment_please);
        }

    }

    @Override
    public void hideProgressDialog() {
        if (!isLoading) {
            dismissDialog();
        } else {
            repos.remove(repos.size() - 1);
        }
    }

    @NonNull
    @Override
    public Context getCurrentContext() {
        return activity;
    }
}
