package xapo.saeed.xapo_test.api.response;

import android.os.Parcel;

import java.util.List;

import androidx.annotation.Nullable;
import xapo.saeed.xapo_test.adapter.AdapterData;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepo implements AdapterData {

    private long id;
    private String node_id;
    private String name;
    private String full_name;
    private String description;
    private String contributors_url;
    private String created_at;
    private String updated_at;
    private String homepage;
    private String language;
    private int open_issues;
    private float score;
    private int watchers_count;
    private int forks_count;


    private GitHubRepoOwner owner;
    private GitHubRepoLicense license;

    private List<GitHubRepoOwner> contributors;


    protected GitHubRepo(Parcel in) {
        id = in.readLong();
        node_id = in.readString();
        name = in.readString();
        full_name = in.readString();
        description = in.readString();
        contributors_url = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        homepage = in.readString();
        language = in.readString();
        open_issues = in.readInt();
        score = in.readFloat();
        watchers_count = in.readInt();
        forks_count = in.readInt();
        owner = in.readParcelable(GitHubRepoOwner.class.getClassLoader());
        license = in.readParcelable(GitHubRepoLicense.class.getClassLoader());
        contributors = in.createTypedArrayList(GitHubRepoOwner.CREATOR);
    }

    public static final Creator<GitHubRepo> CREATOR = new Creator<GitHubRepo>() {
        @Override
        public GitHubRepo createFromParcel(Parcel in) {
            return new GitHubRepo(in);
        }

        @Override
        public GitHubRepo[] newArray(int size) {
            return new GitHubRepo[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContributors_url() {
        return contributors_url;
    }

    public void setContributors_url(String contributors_url) {
        this.contributors_url = contributors_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(int open_issues) {
        this.open_issues = open_issues;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public GitHubRepoOwner getOwner() {
        return owner;
    }

    public void setOwner(GitHubRepoOwner owner) {
        this.owner = owner;
    }

    public GitHubRepoLicense getLicense() {
        return license;
    }

    public void setLicense(GitHubRepoLicense license) {
        this.license = license;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(int watchers_count) {
        this.watchers_count = watchers_count;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public List<GitHubRepoOwner> getContributors() {
        return contributors;
    }

    public void setContributors(List<GitHubRepoOwner> contributors) {
        this.contributors = contributors;
    }

    @Override
    public int getDataType() {
        return REPO_DATA;
    }

    @Nullable
    @Override
    public GitHubRepo getData() {
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(node_id);
        dest.writeString(name);
        dest.writeString(full_name);
        dest.writeString(description);
        dest.writeString(contributors_url);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(homepage);
        dest.writeString(language);
        dest.writeInt(open_issues);
        dest.writeFloat(score);
        dest.writeInt(watchers_count);
        dest.writeInt(forks_count);
        dest.writeParcelable(owner, flags);
        dest.writeParcelable(license, flags);
    }
}
