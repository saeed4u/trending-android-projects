package xapo.saeed.xapo_test.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import xapo.saeed.xapo_test.R;
import xapo.saeed.xapo_test.ui.fragment.GitHubRepoListFragment;

/**
 * Created on 06/11/2018.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.trending_android_repos);
        }
        setUpListFragment();
    }

    private void setUpListFragment() {
        FragmentManager manager = getSupportFragmentManager();
        //First do we have already an instance?
        Fragment fragment = manager.findFragmentByTag(GitHubRepoListFragment.class.getSimpleName());
        if (fragment == null) {
            //No! let's instantiate a new one then
            fragment = new GitHubRepoListFragment();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragments, fragment, GitHubRepoListFragment.class.getSimpleName())
                .addToBackStack(GitHubRepoListFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 0) {
            finish();
        }
    }
}
