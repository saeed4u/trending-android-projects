package xapo.saeed.xapo_test.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import xapo.saeed.xapo_test.R;
import xapo.saeed.xapo_test.api.response.GitHubRepoOwner;

/**
 * Created on 12/11/2018.
 */
public class GitHubContributorViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.contributor_image)
    CircleImageView contributor_image;

    @BindView(R.id.contributor_name)
    TextView contributor_name;

    public GitHubContributorViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(GitHubRepoOwner contributor) {
        Picasso.get().load(contributor.getAvatar_url())
                .into(contributor_image);
        contributor_name.setText(contributor.getLogin());
    }

}
