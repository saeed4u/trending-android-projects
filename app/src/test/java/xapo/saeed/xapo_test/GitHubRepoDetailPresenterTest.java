package xapo.saeed.xapo_test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

import xapo.saeed.xapo_test.api.response.GitHubRepoOwner;
import xapo.saeed.xapo_test.mvp.repo_detail.GitHubRepoContributorsPresenter;
import xapo.saeed.xapo_test.mvp.repo_detail.RepoContributorsListContract;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created on 12/11/2018.
 */
public class GitHubRepoDetailPresenterTest {

    @Mock
    RepoContributorsListContract.Repo model;

    @Mock
    RepoContributorsListContract.View view;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    private RepoContributorsListContract.Presenter presenter;

    @Captor
    private ArgumentCaptor<RepoContributorsListContract.ResponseCallback> argumentCaptor;

    @Before
    public void setUp() {
        presenter = new GitHubRepoContributorsPresenter(model, view);
    }

    @Test
    public void getContributorsSuccess() {
        presenter.getContributors("square/retrofit");
        Mockito.verify(model).getContributors(any(String.class), any(String.class), argumentCaptor.capture());
        argumentCaptor.getValue().success(dummyContributors());
        Mockito.verify(view).hideLoader();
        ArgumentCaptor<List<GitHubRepoOwner>> entityArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(view).success(entityArgumentCaptor.capture());
        assertEquals(1, entityArgumentCaptor.getValue().size());
    }

    @Test
    public void getAndroidRepoFailure() {
        presenter.getContributors("square/retrofit");
        Mockito.verify(model).getContributors(any(String.class), any(String.class), argumentCaptor.capture());
        argumentCaptor.getValue().error(new Exception("An error occurred"));
        Mockito.verify(view).hideLoader();
        ArgumentCaptor<Exception> entityArgumentCaptor = ArgumentCaptor.forClass(Exception.class);
        verify(view).error(entityArgumentCaptor.capture());
    }

    private List<GitHubRepoOwner> dummyContributors() {
        String dummyJson = "[\n" +
                "    {\n" +
                "        \"login\": \"JakeWharton\",\n" +
                "        \"id\": 66577,\n" +
                "        \"node_id\": \"MDQ6VXNlcjY2NTc3\",\n" +
                "        \"avatar_url\": \"https://avatars0.githubusercontent.com/u/66577?v=4\",\n" +
                "        \"gravatar_id\": \"\",\n" +
                "        \"url\": \"https://api.github.com/users/JakeWharton\",\n" +
                "        \"html_url\": \"https://github.com/JakeWharton\",\n" +
                "        \"followers_url\": \"https://api.github.com/users/JakeWharton/followers\",\n" +
                "        \"following_url\": \"https://api.github.com/users/JakeWharton/following{/other_user}\",\n" +
                "        \"gists_url\": \"https://api.github.com/users/JakeWharton/gists{/gist_id}\",\n" +
                "        \"starred_url\": \"https://api.github.com/users/JakeWharton/starred{/owner}{/repo}\",\n" +
                "        \"subscriptions_url\": \"https://api.github.com/users/JakeWharton/subscriptions\",\n" +
                "        \"organizations_url\": \"https://api.github.com/users/JakeWharton/orgs\",\n" +
                "        \"repos_url\": \"https://api.github.com/users/JakeWharton/repos\",\n" +
                "        \"events_url\": \"https://api.github.com/users/JakeWharton/events{/privacy}\",\n" +
                "        \"received_events_url\": \"https://api.github.com/users/JakeWharton/received_events\",\n" +
                "        \"type\": \"User\",\n" +
                "        \"site_admin\": false,\n" +
                "        \"contributions\": 928\n" +
                "    }]";
        return new Gson().fromJson(dummyJson, new TypeToken<List<GitHubRepoOwner>>(){}.getType());
    }


}
