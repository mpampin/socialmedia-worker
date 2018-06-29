package io.redbee.socialmedia.services;

import io.redbee.socialmedia.entities.Post;

import java.util.List;

public interface PostPublishService {
    void publish(List<Post> posts, String query);
}
