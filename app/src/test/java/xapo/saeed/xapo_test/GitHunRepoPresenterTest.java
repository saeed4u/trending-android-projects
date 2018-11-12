package xapo.saeed.xapo_test;

import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import xapo.saeed.xapo_test.api.request.GitHubAPI;
import xapo.saeed.xapo_test.mvp.repo_list.RepoListContract;
import xapo.saeed.xapo_test.mvp.repo_list.GitHubRepoListPresenter;

/**
 * Created on 07/11/2018.
 */
public class GitHunRepoPresenterTest {

    private static final String SORT = "DESC";
    private static final String ORDER_BY = "B";
    private static final int PER_PAGE = 10;
    private static final int PAGE = 1;

    @Mock
    RepoListContract.Repo model;

    @Mock
    RepoListContract.View view;

    @Mock
    GitHubAPI api;


    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    GitHubRepoListPresenter presenter;

 /*   @Before
    public void init() {
        presenter = new GitHubRepoListPresenter(view, model);
        presenter.getRepo(SORT, ORDER_BY, PER_PAGE, PAGE);
    }


    @Test
    public void verifyThatShowProgressDialogIsCalledOnView() {
        Mockito.verify(view).showLoader();
    }

    @Test
    public void verifyThatGetRepoIsCalledOnModel() {
        Mockito.verify(model).getAndroidRepo(SORT, ORDER_BY, PER_PAGE, PAGE);
        Mockito.verify(view).hideLoader();
    }

    @Test
    public void verifyThatHideProgressIsCalledOnView() {
        GitHubRepoResponse gitHubRepoResponse = new GitHubRepoResponse();
        Mockito.when(api.getTrendingRepos(.ANDROID_QUERY_STRING, SORT, ORDER_BY, PER_PAGE, PAGE)).thenReturn(Observable.just(gitHubRepoResponse));
        presenter.getRepo(SORT, ORDER_BY, PER_PAGE, PAGE);
        Mockito.verify(view).hideProgressDialog();
    }*/

}
