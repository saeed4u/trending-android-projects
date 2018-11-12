package xapo.saeed.xapo_test;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import xapo.saeed.xapo_test.api.response.GitHubRepo;
import xapo.saeed.xapo_test.ui.RepoDetailActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created on 12/11/2018.
 */
@RunWith(AndroidJUnit4.class)
public class RepoDetailActivityTest {

    private GitHubRepo repo;

    @Rule
    public ActivityTestRule<RepoDetailActivity> repoDetailActivityActivityTestRule = new ActivityTestRule<>(RepoDetailActivity.class, false, false);

    private static final Intent REPO_DETAIL_ACTIVITY_INTENT = new Intent(InstrumentationRegistry.getTargetContext(), RepoDetailActivity.class);

    @Before
    public void setUp() {
        REPO_DETAIL_ACTIVITY_INTENT.putExtra(RepoDetailActivity.GITHUB_REPO, dummyRepo());
        repoDetailActivityActivityTestRule.launchActivity(REPO_DETAIL_ACTIVITY_INTENT);
        Intent intent = repoDetailActivityActivityTestRule.getActivity().getIntent();
        repo = intent.getParcelableExtra(RepoDetailActivity.GITHUB_REPO);
    }

    @Test
    public void checkForTitleOfRepoIsDisplayed() {
        onView(withText(repo.getName() + " by " + repo.getOwner().getLogin())).check(matches(isDisplayed()));
    }

    @Test
    public void checkForDescriptionOfRepoIsDisplayed() {
        onView(withText(repo.getDescription())).check(matches(isDisplayed()));
    }

    @Test
    public void checkForStatsOfRepoIsDisplayed() {
        Context context = repoDetailActivityActivityTestRule.getActivity();
        onView(withText(context.getString(R.string.repo_watchers, repo.getWatchers_count()))).check(matches(isDisplayed()));
        onView(withText(context.getString(R.string.repo_forks, repo.getForks_count()))).check(matches(isDisplayed()));
        onView(withText(context.getString(R.string.repo_issues, repo.getOpen_issues()))).check(matches(isDisplayed()));
        onView(withText(context.getString(R.string.repo_score, repo.getScore()))).check(matches(isDisplayed()));
    }

    @Test
    public void checkForContributorLabelIsDisplayed() {
        onView(withText(R.string.repo_contributors)).check(matches(isDisplayed()));
    }

    private GitHubRepo dummyRepo() {
        String dummyRepoJson = " {\n" +
                "            \"id\": 12544093,\n" +
                "            \"node_id\": \"MDEwOlJlcG9zaXRvcnkxMjU0NDA5Mw==\",\n" +
                "            \"name\": \"Android\",\n" +
                "            \"full_name\": \"hmkcode/Android\",\n" +
                "            \"private\": false,\n" +
                "            \"owner\": {\n" +
                "                \"login\": \"hmkcode\",\n" +
                "                \"id\": 3790597,\n" +
                "                \"node_id\": \"MDQ6VXNlcjM3OTA1OTc=\",\n" +
                "                \"avatar_url\": \"https://avatars3.githubusercontent.com/u/3790597?v=4\",\n" +
                "                \"gravatar_id\": \"\",\n" +
                "                \"url\": \"https://api.github.com/users/hmkcode\",\n" +
                "                \"html_url\": \"https://github.com/hmkcode\",\n" +
                "                \"followers_url\": \"https://api.github.com/users/hmkcode/followers\",\n" +
                "                \"following_url\": \"https://api.github.com/users/hmkcode/following{/other_user}\",\n" +
                "                \"gists_url\": \"https://api.github.com/users/hmkcode/gists{/gist_id}\",\n" +
                "                \"starred_url\": \"https://api.github.com/users/hmkcode/starred{/owner}{/repo}\",\n" +
                "                \"subscriptions_url\": \"https://api.github.com/users/hmkcode/subscriptions\",\n" +
                "                \"organizations_url\": \"https://api.github.com/users/hmkcode/orgs\",\n" +
                "                \"repos_url\": \"https://api.github.com/users/hmkcode/repos\",\n" +
                "                \"events_url\": \"https://api.github.com/users/hmkcode/events{/privacy}\",\n" +
                "                \"received_events_url\": \"https://api.github.com/users/hmkcode/received_events\",\n" +
                "                \"type\": \"User\",\n" +
                "                \"site_admin\": false\n" +
                "            },\n" +
                "            \"html_url\": \"https://github.com/hmkcode/Android\",\n" +
                "            \"description\": \"Android related examples\",\n" +
                "            \"fork\": false,\n" +
                "            \"url\": \"https://api.github.com/repos/hmkcode/Android\",\n" +
                "            \"forks_url\": \"https://api.github.com/repos/hmkcode/Android/forks\",\n" +
                "            \"keys_url\": \"https://api.github.com/repos/hmkcode/Android/keys{/key_id}\",\n" +
                "            \"collaborators_url\": \"https://api.github.com/repos/hmkcode/Android/collaborators{/collaborator}\",\n" +
                "            \"teams_url\": \"https://api.github.com/repos/hmkcode/Android/teams\",\n" +
                "            \"hooks_url\": \"https://api.github.com/repos/hmkcode/Android/hooks\",\n" +
                "            \"issue_events_url\": \"https://api.github.com/repos/hmkcode/Android/issues/events{/number}\",\n" +
                "            \"events_url\": \"https://api.github.com/repos/hmkcode/Android/events\",\n" +
                "            \"assignees_url\": \"https://api.github.com/repos/hmkcode/Android/assignees{/user}\",\n" +
                "            \"branches_url\": \"https://api.github.com/repos/hmkcode/Android/branches{/branch}\",\n" +
                "            \"tags_url\": \"https://api.github.com/repos/hmkcode/Android/tags\",\n" +
                "            \"blobs_url\": \"https://api.github.com/repos/hmkcode/Android/git/blobs{/sha}\",\n" +
                "            \"git_tags_url\": \"https://api.github.com/repos/hmkcode/Android/git/tags{/sha}\",\n" +
                "            \"git_refs_url\": \"https://api.github.com/repos/hmkcode/Android/git/refs{/sha}\",\n" +
                "            \"trees_url\": \"https://api.github.com/repos/hmkcode/Android/git/trees{/sha}\",\n" +
                "            \"statuses_url\": \"https://api.github.com/repos/hmkcode/Android/statuses/{sha}\",\n" +
                "            \"languages_url\": \"https://api.github.com/repos/hmkcode/Android/languages\",\n" +
                "            \"stargazers_url\": \"https://api.github.com/repos/hmkcode/Android/stargazers\",\n" +
                "            \"contributors_url\": \"https://api.github.com/repos/hmkcode/Android/contributors\",\n" +
                "            \"subscribers_url\": \"https://api.github.com/repos/hmkcode/Android/subscribers\",\n" +
                "            \"subscription_url\": \"https://api.github.com/repos/hmkcode/Android/subscription\",\n" +
                "            \"commits_url\": \"https://api.github.com/repos/hmkcode/Android/commits{/sha}\",\n" +
                "            \"git_commits_url\": \"https://api.github.com/repos/hmkcode/Android/git/commits{/sha}\",\n" +
                "            \"comments_url\": \"https://api.github.com/repos/hmkcode/Android/comments{/number}\",\n" +
                "            \"issue_comment_url\": \"https://api.github.com/repos/hmkcode/Android/issues/comments{/number}\",\n" +
                "            \"contents_url\": \"https://api.github.com/repos/hmkcode/Android/contents/{+path}\",\n" +
                "            \"compare_url\": \"https://api.github.com/repos/hmkcode/Android/compare/{base}...{head}\",\n" +
                "            \"merges_url\": \"https://api.github.com/repos/hmkcode/Android/merges\",\n" +
                "            \"archive_url\": \"https://api.github.com/repos/hmkcode/Android/{archive_format}{/ref}\",\n" +
                "            \"downloads_url\": \"https://api.github.com/repos/hmkcode/Android/downloads\",\n" +
                "            \"issues_url\": \"https://api.github.com/repos/hmkcode/Android/issues{/number}\",\n" +
                "            \"pulls_url\": \"https://api.github.com/repos/hmkcode/Android/pulls{/number}\",\n" +
                "            \"milestones_url\": \"https://api.github.com/repos/hmkcode/Android/milestones{/number}\",\n" +
                "            \"notifications_url\": \"https://api.github.com/repos/hmkcode/Android/notifications{?since,all,participating}\",\n" +
                "            \"labels_url\": \"https://api.github.com/repos/hmkcode/Android/labels{/name}\",\n" +
                "            \"releases_url\": \"https://api.github.com/repos/hmkcode/Android/releases{/id}\",\n" +
                "            \"deployments_url\": \"https://api.github.com/repos/hmkcode/Android/deployments\",\n" +
                "            \"created_at\": \"2013-09-02T16:12:28Z\",\n" +
                "            \"updated_at\": \"2018-11-11T17:17:55Z\",\n" +
                "            \"pushed_at\": \"2018-10-31T09:31:09Z\",\n" +
                "            \"git_url\": \"git://github.com/hmkcode/Android.git\",\n" +
                "            \"ssh_url\": \"git@github.com:hmkcode/Android.git\",\n" +
                "            \"clone_url\": \"https://github.com/hmkcode/Android.git\",\n" +
                "            \"svn_url\": \"https://github.com/hmkcode/Android\",\n" +
                "            \"homepage\": null,\n" +
                "            \"size\": 1838,\n" +
                "            \"stargazers_count\": 2647,\n" +
                "            \"watchers_count\": 2647,\n" +
                "            \"language\": \"Java\",\n" +
                "            \"has_issues\": true,\n" +
                "            \"has_projects\": true,\n" +
                "            \"has_downloads\": true,\n" +
                "            \"has_wiki\": true,\n" +
                "            \"has_pages\": false,\n" +
                "            \"forks_count\": 3265,\n" +
                "            \"mirror_url\": null,\n" +
                "            \"archived\": false,\n" +
                "            \"open_issues_count\": 36,\n" +
                "            \"license\": null,\n" +
                "            \"forks\": 3265,\n" +
                "            \"open_issues\": 36,\n" +
                "            \"watchers\": 2647,\n" +
                "            \"default_branch\": \"master\",\n" +
                "            \"score\": 114.28522\n" +
                "        }";

        return new Gson().fromJson(dummyRepoJson, GitHubRepo.class);
    }


}
