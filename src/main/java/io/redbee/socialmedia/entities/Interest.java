package io.redbee.socialmedia.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Interest {

    private String interest;
    private Date lastTimeQueried;
    private List<Post> posts;

    public Interest(String interest) {
        this.interest = interest;
        this.posts = new ArrayList<>();
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Date getLastTimeQueried() {
        return lastTimeQueried;
    }

    public void setLastTimeQueried(Date lastTimeQueried) {
        this.lastTimeQueried = lastTimeQueried;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Long getLastIdQueried() {
        if(posts == null || posts.size() == 0) return 0L;
        return posts.get(0).getId();
    }

    public void addPosts(List<Post> posts) {
        this.posts.addAll(0, posts);
    }
}


