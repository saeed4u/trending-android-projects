package xapo.saeed.xapo_test.api.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoLicense implements Parcelable {

    private String key;
    private String name;
    private String spdx_id;
    private String url;

    protected GitHubRepoLicense(Parcel in) {
        key = in.readString();
        name = in.readString();
        spdx_id = in.readString();
        url = in.readString();
    }

    public static final Creator<GitHubRepoLicense> CREATOR = new Creator<GitHubRepoLicense>() {
        @Override
        public GitHubRepoLicense createFromParcel(Parcel in) {
            return new GitHubRepoLicense(in);
        }

        @Override
        public GitHubRepoLicense[] newArray(int size) {
            return new GitHubRepoLicense[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpdx_id() {
        return spdx_id;
    }

    public void setSpdx_id(String spdx_id) {
        this.spdx_id = spdx_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(spdx_id);
        dest.writeString(url);
    }
}
