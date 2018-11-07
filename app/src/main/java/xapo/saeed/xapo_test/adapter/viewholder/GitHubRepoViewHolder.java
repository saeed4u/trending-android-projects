package xapo.saeed.xapo_test.adapter.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import xapo.saeed.xapo_test.R;
import xapo.saeed.xapo_test.api.response.GitHubRepo;
import xapo.saeed.xapo_test.api.response.GitHubRepoOwner;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_image)
    CircleImageView user_image;

    @BindView(R.id.repo_name)
    TextView repo_name;

    @BindView(R.id.repo_description)
    TextView repo_description;

    @BindView(R.id.repo_owner)
    TextView repo_owner;

    @BindView(R.id.image_loader)
    View image_loader;

    @BindView(R.id.user_image_holder)
    View user_image_holder;

    public GitHubRepoViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull GitHubRepo repo) {
        repo_name.setText(repo.getName());
        repo_description.setText(repo.getDescription());

        GitHubRepoOwner repoOwner = repo.getOwner();
        repo_owner.setText(repoOwner.getLogin());
        if (TextUtils.isEmpty(repoOwner.getAvatar_url())) {
            user_image_holder.setVisibility(View.GONE);
        } else {
            Picasso.get()
                    .load(repoOwner.getAvatar_url())
                    .into(user_image, new Callback() {
                        @Override
                        public void onSuccess() {
                            image_loader.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                            user_image_holder.setVisibility(View.GONE);
                        }
                    });
        }
    }

}
