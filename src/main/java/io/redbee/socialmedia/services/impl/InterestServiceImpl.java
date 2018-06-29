package io.redbee.socialmedia.services.impl;

import io.redbee.socialmedia.entities.Interest;
import io.redbee.socialmedia.entities.Post;
import io.redbee.socialmedia.providers.Provider;
import io.redbee.socialmedia.repositories.InterestRepository;
import io.redbee.socialmedia.services.InterestService;
import io.redbee.socialmedia.services.PostPublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class InterestServiceImpl implements InterestService {

    private static final Logger log = LoggerFactory.getLogger(InterestServiceImpl.class);

    private final Provider provider;
    private final InterestRepository repository;
    private final PostPublishService postPublishService;

    @Autowired
    public InterestServiceImpl(@Qualifier("twitterProvider") Provider provider, InterestRepository repository, PostPublishService postPublishService) {
        this.provider = provider;
        this.repository = repository;
        this.postPublishService = postPublishService;
    }

    @Override
    public List<Post> updateInterestPosts(Interest interest) {

        List<Post> posts = provider.queryUpdates(interest);

        interest.setLastTimeQueried(new Date());

        if(posts.size() > 0) {
            interest.addPosts(posts);
        }

        return posts;
    }

    @Override
    public Interest queryInterest(String query) {

        Interest interest = repository.findInterestByQuery(query);
        if(interest == null) {
            interest = new Interest(query);
            updateInterestPosts(interest);
            repository.save(interest);
        }

        return interest;
    }

    @Override
    @Async("postUpdaterTaskExecutor")
    public CompletableFuture<List<Post>> updateInterest(Interest interest) {
        log.info("Updating interests " + interest.getQuery());

        List<Post> posts = this.updateInterestPosts(interest);
        repository.save(interest);

        postPublishService.publish(posts, interest.getQuery());

        return CompletableFuture.completedFuture(posts);

    }



}
