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
import xapo.saeed.xapo_test.adapter.viewholder.LoadingDataViewHolder;
import xapo.saeed.xapo_test.util.ItemAnimation;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoAdapter extends RecyclerView.Adapter {

    private List<AdapterData> data;
    private int lastPosition = -1;
    private boolean onAttach = true;

    public GitHubRepoAdapter(List<AdapterData> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getDataType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case AdapterData.LOADING_DATA:
                return new LoadingDataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_data_item, parent, false));
            default:
                return new GitHubRepoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterData adapterData = data.get(position);
        if (adapterData.getDataType() == AdapterData.REPO_DATA) {
            if (adapterData.getData() != null) {
                GitHubRepoViewHolder viewHolder = (GitHubRepoViewHolder) holder;
                viewHolder.bind(adapterData.getData());
                setAnimation(holder.itemView, position);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
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
