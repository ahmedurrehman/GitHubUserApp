package com.ahmed.githubuser.Model;


import android.os.Parcel;
import android.os.Parcelable;

public class GitHubUserInfo implements Parcelable {

    private String gistsUrl;

    private String reposUrl;

    private String followingUrl;

    private String bio;

    private String createdAt;

    private String login;

    private String type;

    private String blog;

    private String subscriptionsUrl;

    private String updatedAt;

    private boolean siteAdmin;

    private String company;

    private int id;

    private int publicRepos;

    private String gravatarId;

    private String email;

    private String organizationsUrl;

    private Object hireable;

    private String starredUrl;

    private String followersUrl;

    private int publicGists;

    private String url;

    private String receivedEventsUrl;

    private int followers;

    private String avatarUrl;

    private String eventsUrl;

    private String htmlUrl;

    private int following;

    private String name;

    private String location;

    private String nodeId;

    public GitHubUserInfo() {
    }


    protected GitHubUserInfo(Parcel in) {
        gistsUrl = in.readString();
        reposUrl = in.readString();
        followingUrl = in.readString();
        bio = in.readString();
        createdAt = in.readString();
        login = in.readString();
        type = in.readString();
        blog = in.readString();
        subscriptionsUrl = in.readString();
        updatedAt = in.readString();
        siteAdmin = in.readByte() != 0;
        company = in.readString();
        id = in.readInt();
        publicRepos = in.readInt();
        gravatarId = in.readString();
        email = in.readString();
        organizationsUrl = in.readString();
        starredUrl = in.readString();
        followersUrl = in.readString();
        publicGists = in.readInt();
        url = in.readString();
        receivedEventsUrl = in.readString();
        followers = in.readInt();
        avatarUrl = in.readString();
        eventsUrl = in.readString();
        htmlUrl = in.readString();
        following = in.readInt();
        name = in.readString();
        location = in.readString();
        nodeId = in.readString();
    }

    public static final Creator<GitHubUserInfo> CREATOR = new Creator<GitHubUserInfo>() {
        @Override
        public GitHubUserInfo createFromParcel(Parcel in) {
            return new GitHubUserInfo(in);
        }

        @Override
        public GitHubUserInfo[] newArray(int size) {
            return new GitHubUserInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(gistsUrl);
        parcel.writeString(reposUrl);
        parcel.writeString(followingUrl);
        parcel.writeString(bio);
        parcel.writeString(createdAt);
        parcel.writeString(login);
        parcel.writeString(type);
        parcel.writeString(blog);
        parcel.writeString(subscriptionsUrl);
        parcel.writeString(updatedAt);
        parcel.writeByte((byte) (siteAdmin ? 1 : 0));
        parcel.writeString(company);
        parcel.writeInt(id);
        parcel.writeInt(publicRepos);
        parcel.writeString(gravatarId);
        parcel.writeString(email);
        parcel.writeString(organizationsUrl);
        parcel.writeString(starredUrl);
        parcel.writeString(followersUrl);
        parcel.writeInt(publicGists);
        parcel.writeString(url);
        parcel.writeString(receivedEventsUrl);
        parcel.writeInt(followers);
        parcel.writeString(avatarUrl);
        parcel.writeString(eventsUrl);
        parcel.writeString(htmlUrl);
        parcel.writeInt(following);
        parcel.writeString(name);
        parcel.writeString(location);
        parcel.writeString(nodeId);
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        if (email.equalsIgnoreCase("null"))
            email = "No Email";
        this.email = email;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public Object getHireable() {
        return hireable;
    }

    public void setHireable(Object hireable) {
        this.hireable = hireable;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public int getPublicGists() {
        return publicGists;
    }

    public void setPublicGists(int publicGists) {
        this.publicGists = publicGists;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getName() {

        if (name.equalsIgnoreCase("null"))
            name = "No Name";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}