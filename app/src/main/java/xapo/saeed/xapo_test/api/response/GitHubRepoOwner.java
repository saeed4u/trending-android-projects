package xapo.saeed.xapo_test.api.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 06/11/2018.
 */
public class GitHubRepoOwner implements Parcelable {

    private String avatar_url;
    private String url;
    private String html_url;
    private String followers_url;
    private String repos_url;
    private String type;
    private String login;


    protected GitHubRepoOwner(Parcel in) {
        avatar_url = in.readString();
        url = in.readString();
        html_url = in.readString();
        followers_url = in.readString();
        repos_url = in.readString();
        type = in.readString();
        login = in.readString();
    }

    public static final Creator<GitHubRepoOwner> CREATOR = new Creator<GitHubRepoOwner>() {
        @Override
        public GitHubRepoOwner createFromParcel(Parcel in) {
            return new GitHubRepoOwner(in);
        }

        @Override
        public GitHubRepoOwner[] newArray(int size) {
            return new GitHubRepoOwner[size];
        }
    };

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatar_url);
        dest.writeString(url);
        dest.writeString(html_url);
        dest.writeString(followers_url);
        dest.writeString(repos_url);
        dest.writeString(type);
        dest.writeString(login);
    }
}
