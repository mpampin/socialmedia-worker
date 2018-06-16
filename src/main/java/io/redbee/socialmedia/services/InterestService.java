package io.redbee.socialmedia.services;

import io.redbee.socialmedia.entities.Interest;
import io.redbee.socialmedia.entities.Post;

import java.util.List;

public interface InterestService {

    List<Post> queryInterestPosts(Interest interest);
    Interest queryInterest(String interest);
}
