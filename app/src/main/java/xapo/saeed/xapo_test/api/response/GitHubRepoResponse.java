package xapo.saeed.xapo_test.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoResponse implements Parcelable {

    private long total_count;
    private boolean incomplete_results;

    private List<GitHubRepo> items;

    public GitHubRepoResponse() {
    }

    protected GitHubRepoResponse(Parcel in) {
        total_count = in.readLong();
        incomplete_results = in.readByte() != 0;
        items = in.createTypedArrayList(GitHubRepo.CREATOR);
    }

    public static final Creator<GitHubRepoResponse> CREATOR = new Creator<GitHubRepoResponse>() {
        @Override
        public GitHubRepoResponse createFromParcel(Parcel in) {
            return new GitHubRepoResponse(in);
        }

        @Override
        public GitHubRepoResponse[] newArray(int size) {
            return new GitHubRepoResponse[size];
        }
    };

    public long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(long total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<GitHubRepo> getItems() {
        return items;
    }

    public void setItems(List<GitHubRepo> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(total_count);
        dest.writeByte((byte) (incomplete_results ? 1 : 0));
        dest.writeTypedList(items);
    }
}
