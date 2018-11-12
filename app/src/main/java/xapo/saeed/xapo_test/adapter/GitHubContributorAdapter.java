package xapo.saeed.xapo_test.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import xapo.saeed.xapo_test.R;
import xapo.saeed.xapo_test.adapter.viewholder.GitHubContributorViewHolder;
import xapo.saeed.xapo_test.api.response.GitHubRepoOwner;

/**
 * Created on 12/11/2018.
 */
public class GitHubContributorAdapter extends RecyclerView.Adapter<GitHubContributorViewHolder> {

    private List<GitHubRepoOwner> contributors;

    public GitHubContributorAdapter(List<GitHubRepoOwner> contributors) {
        this.contributors = contributors;
    }

    @NonNull
    @Override
    public GitHubContributorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GitHubContributorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contributor_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubContributorViewHolder holder, int position) {
        holder.bind(contributors.get(position));
    }

    @Override
    public int getItemCount() {
        return contributors.size();
    }
}
