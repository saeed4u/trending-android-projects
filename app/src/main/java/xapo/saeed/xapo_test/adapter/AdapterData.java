package xapo.saeed.xapo_test.adapter;

import android.os.Parcelable;

import androidx.annotation.Nullable;
import xapo.saeed.xapo_test.api.response.GitHubRepo;

/**
 * Created on 07/11/2018.
 */
public interface AdapterData extends Parcelable {

    int LOADING_DATA = 0;
    int REPO_DATA = 1;

    int getDataType();

    @Nullable
    GitHubRepo getData();
}
