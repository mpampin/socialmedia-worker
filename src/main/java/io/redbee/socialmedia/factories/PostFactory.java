package io.redbee.socialmedia.factories;

import io.redbee.socialmedia.entities.Post;
import twitter4j.HashtagEntity;
import twitter4j.User;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class PostFactory {

    private Post post;

    public PostFactory() {
        this.post = new Post();
    }

    public PostFactory withUser(User user) {
        post.setUser(user.getName());
        return this;
    }

    public PostFactory withMessage(String message) {
        post.setMessage(message);
        return this;
    }

    public PostFactory withHashtags(HashtagEntity[] hashtags) {

        Set<String> mappedHashtags = Arrays.stream(hashtags)
                .map(HashtagEntity::getText)
                .collect(Collectors.toSet());;

        post.setHashtags(mappedHashtags);
        return this;
    }

    public Post build() {
        return post;
    }

    public PostFactory withId(long id) {
        post.setId(id);
        return this;
    }
}
