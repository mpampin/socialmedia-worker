package io.redbee.socialmedia.services.impl;

import io.redbee.socialmedia.entities.Post;
import io.redbee.socialmedia.providers.TwitterProvider;
import io.redbee.socialmedia.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private TwitterProvider twitterProvider;

    @Autowired
    public PostServiceImpl(TwitterProvider twitterProvider) {
        this.twitterProvider = twitterProvider;
    }

    @Override
    public List<Post> getPostsForInterest(String interest) {

        return twitterProvider.query(interest, null);
    }
}
