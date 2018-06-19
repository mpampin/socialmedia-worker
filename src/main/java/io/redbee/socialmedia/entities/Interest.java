package io.redbee.socialmedia.entities;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Interest {

    @Id
    private String id;
    private String query;
    private Date lastTimeQueried;
    private List<Post> posts;

    public Interest(String query) {
        this.query = query;
        this.posts = new ArrayList<>();
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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

    public void addPost(Post post) {
        this.posts.add(post);
    }
}


