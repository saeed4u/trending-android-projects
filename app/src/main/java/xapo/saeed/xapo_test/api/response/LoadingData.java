package xapo.saeed.xapo_test.api.response;

import android.annotation.SuppressLint;
import android.os.Parcel;

import androidx.annotation.Nullable;
import xapo.saeed.xapo_test.adapter.AdapterData;

/**
 * Created on 07/11/2018.
 */
@SuppressLint("ParcelCreator")
public class LoadingData implements AdapterData {

    @Override
    public int getDataType() {
        return LOADING_DATA;
    }

    @Nullable
    @Override
    public GitHubRepo getData() {
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
