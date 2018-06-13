package io.redbee.socialmedia.entities;

import java.util.Set;

public class Post {

    private String user;
    private String message;
    private Set<String> hashtags;
    private EmbedInfo embedInfo;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<String> hashtags) {
        this.hashtags = hashtags;
    }

    public EmbedInfo getEmbedInfo() {
        return embedInfo;
    }

    public void setEmbedInfo(EmbedInfo embedInfo) {
        this.embedInfo = embedInfo;
    }
}
