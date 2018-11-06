package xapo.saeed.xapo_test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import xapo.saeed.xapo_test.R;
import xapo.saeed.xapo_test.adapter.viewholder.GitHubRepoViewHolder;
import xapo.saeed.xapo_test.api.response.GitHubRepo;
import xapo.saeed.xapo_test.util.ItemAnimation;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoViewHolder> {

    private List<GitHubRepo> repos;
    private int lastPosition = -1;
    private boolean onAttach = true;

    public GitHubRepoAdapter(List<GitHubRepo> repos) {
        this.repos = repos;
    }

    @NonNull
    @Override
    public GitHubRepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GitHubRepoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubRepoViewHolder holder, int position) {
        holder.bind(repos.get(position));
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                onAttach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, onAttach ? position : -1, ItemAnimation.RIGHT_LEFT);
            lastPosition = position;
        }
    }

}
