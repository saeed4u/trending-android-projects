package xapo.saeed.xapo_test;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import xapo.saeed.xapo_test.api.response.GitHubRepo;
import xapo.saeed.xapo_test.api.response.GitHubRepoResponse;
import xapo.saeed.xapo_test.model.QueryParam;
import xapo.saeed.xapo_test.mvp.repo_list.RepoListContract;
import xapo.saeed.xapo_test.presenter.GitHubRepoPresenter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created on 07/11/2018.
 */
public class GitHubRepoPresenterTest {

    private static final String SORT = "DESC";
    private static final String ORDER_BY = "B";
    private static final int PER_PAGE = 10;
    private static final int PAGE = 1;

    @Mock
    RepoListContract.Repo model;

    @Mock
    RepoListContract.View view;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    private RepoListContract.Presenter presenter;

    @Captor
    private ArgumentCaptor<RepoListContract.ResponseCallback> argumentCaptor;

    @Before
    public void setUp() {
        presenter = new GitHubRepoPresenter(view, model);
    }

    @Test
    public void getAndroidReposSuccess() {
        QueryParam param = new QueryParam(SORT, ORDER_BY, PER_PAGE, PAGE);
        presenter.getRepo(param);
        Mockito.verify(model).getRepo(Mockito.any(QueryParam.class), argumentCaptor.capture());
        argumentCaptor.getValue().success(dummyRepos());
        Mockito.verify(view).hideLoader();
        ArgumentCaptor<List<GitHubRepo>> entityArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(view).success(entityArgumentCaptor.capture());
        assertEquals(0, entityArgumentCaptor.getValue().size());
    }

    @Test
    public void getAndroidRepoFailure() {
        QueryParam param = new QueryParam(SORT, ORDER_BY, PER_PAGE, PAGE);
        presenter.getRepo(param);
        Mockito.verify(model).getRepo(Mockito.any(QueryParam.class), argumentCaptor.capture());
        argumentCaptor.getValue().error(new Exception("An error occurred"));
        Mockito.verify(view).hideLoader();
        ArgumentCaptor<Exception> entityArgumentCaptor = ArgumentCaptor.forClass(Exception.class);
        verify(view).error(entityArgumentCaptor.capture());
    }

    private List<GitHubRepo> dummyRepos() {
        String dummyJson = "{\"items\": []}";
        return new Gson().fromJson(dummyJson, GitHubRepoResponse.class).getItems();
    }


}
