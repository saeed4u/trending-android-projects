package xapo.saeed.xapo_test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import xapo.saeed.xapo_test.R;
import xapo.saeed.xapo_test.adapter.GitHubContributorAdapter;
import xapo.saeed.xapo_test.api.response.GitHubRepo;
import xapo.saeed.xapo_test.api.response.GitHubRepoOwner;
import xapo.saeed.xapo_test.mvp.repo_detail.GitHubRepoContributorsModel;
import xapo.saeed.xapo_test.mvp.repo_detail.GitHubRepoContributorsPresenter;
import xapo.saeed.xapo_test.mvp.repo_detail.RepoContributorsListContract;

/**
 * Created on 11/11/2018.
 */
public class RepoDetailActivity extends AppCompatActivity implements RepoContributorsListContract.View {

    public static final String GITHUB_REPO = "repo";

    private static final int MAX_CONTRIBUTORS_TO_SHOW = 5;

    @BindView(R.id.repo_image)
    ImageView repo_image;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.repo_name_and_owner)
    TextView repo_name_and_owner;

    @BindView(R.id.repo_description)
    TextView repo_description;

    @BindView(R.id.repo_full_name)
    TextView repo_full_name;

    @BindView(R.id.contributors)
    RecyclerView contributors;

    @BindView(R.id.contributors_label)
    TextView contributors_label;

    @BindView(R.id.forks_count)
    TextView forks_count;

    @BindView(R.id.watchers_count)
    TextView watchers_count;

    @BindView(R.id.issues_count)
    TextView issues_count;

    @BindView(R.id.score)
    TextView score;

    @BindView(R.id.license_layout)
    View license_layout;

    @BindView(R.id.license)
    TextView license;

    @BindView(R.id.contributors_loader)
    View contributors_loader;

    @BindView(R.id.repo_contributors_view)
    View repo_contributors_view;

    private GitHubRepo repo;

    private GitHubContributorAdapter contributorAdapter;
    private List<GitHubRepoOwner> contributorList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repo_detail_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(null);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState != null) {
            repo = savedInstanceState.getParcelable(GITHUB_REPO);
        }

        if (repo == null) {
            Intent intent = getIntent();
            repo = intent.getParcelableExtra(GITHUB_REPO);
        }
        init();
    }

    private void init() {
        Picasso.get()
                .load(repo.getOwner().getAvatar_url())
                .fit()
                .into(repo_image);

        repo_full_name.setText(repo.getFull_name());
        repo_name_and_owner.setText(getString(R.string.repo_name_and_owner, repo.getName(), repo.getOwner().getLogin()));
        repo_description.setText(repo.getDescription());

        score.setText(getString(R.string.repo_score, repo.getScore()));
        watchers_count.setText(getString(R.string.repo_watchers, repo.getWatchers_count()));
        forks_count.setText(getString(R.string.repo_forks, repo.getForks_count()));
        issues_count.setText(getString(R.string.repo_issues, repo.getOpen_issues()));

        if (repo.getLicense() != null) {
            license.setText(repo.getLicense().getName());
            license_layout.setVisibility(View.VISIBLE);
        }
        List<GitHubRepoOwner> contributors = repo.getContributors();
        contributorList = new ArrayList<>();
        if (contributors == null || contributors.isEmpty()) {
            RepoContributorsListContract.Presenter presenter = new GitHubRepoContributorsPresenter(new GitHubRepoContributorsModel(), this);
            presenter.getContributors(repo.getFull_name());
        } else {
            pluckMaxContributors(contributors);
        }
        contributorAdapter = new GitHubContributorAdapter(contributorList);
        this.contributors.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        this.contributors.setAdapter(contributorAdapter);
        if (!contributorList.isEmpty()) {
            this.contributors.setVisibility(View.VISIBLE);
            setContributorsLabel();
        }
    }

    private void setContributorsLabel() {
        if (contributorList.size() == 1){
            contributors_label.setText(R.string.top_contributor);
        }else {
            contributors_label.setText(getString(R.string.top_d_contributors, this.contributorList.size()));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(GITHUB_REPO, repo);
    }

    @Override
    public void showLoader() {
        contributors_loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        contributors_loader.setVisibility(View.GONE);
    }

    @Override
    public void success(@NonNull List<GitHubRepoOwner> contributors) {
        repo.setContributors(contributors);
        contributorList.clear();
        pluckMaxContributors(contributors);
        contributorAdapter.notifyDataSetChanged();
        this.contributors.setVisibility(View.VISIBLE);
        setContributorsLabel();
    }

    private void pluckMaxContributors(@NonNull List<GitHubRepoOwner> contributors) {
        for (int i = 0; i < contributors.size(); i++) {
            if (i == MAX_CONTRIBUTORS_TO_SHOW) {
                break;
            }
            contributorList.add(contributors.get(i));
        }
    }

    @Override
    public void error(Throwable throwable) {
        Toast.makeText(this, R.string.oops_error_occurred, Toast.LENGTH_SHORT).show();
        repo_contributors_view.setVisibility(View.GONE);
    }
}
