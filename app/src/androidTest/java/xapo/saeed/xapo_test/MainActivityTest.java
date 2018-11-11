package xapo.saeed.xapo_test;

import android.content.Intent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import xapo.saeed.xapo_test.ui.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created on 11/11/2018.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    private static final Intent MAIN_ACTIVITY_INTENT = new Intent(InstrumentationRegistry.getTargetContext(), MainActivity.class);

    @Before
    public void setUp() {
        mainActivityActivityTestRule.launchActivity(MAIN_ACTIVITY_INTENT);
    }

    @Test
    public void checkForActivityTitleWhenFragmentIsGitHubRepoListFragment() {
        onView(withText(R.string.trending_android_repos)).check(matches(isDisplayed()));
    }

    @Test
    public void checkRecyclerViewVisibility() {
        onView(withId(R.id.android_repos)).check(matches(isDisplayed()));
    }


}
