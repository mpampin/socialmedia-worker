package io.redbee.socialmedia.services;

import io.redbee.socialmedia.entities.Interest;
import io.redbee.socialmedia.entities.Post;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface InterestService {

    List<Post> updateInterestPosts(Interest interest);
    Interest queryInterest(String interest);

    @Async("postUpdaterTaskExecutor")
    CompletableFuture<List<Post>> updateInterest(Interest interest);
}
