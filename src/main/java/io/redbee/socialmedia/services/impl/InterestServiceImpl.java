package io.redbee.socialmedia.services.impl;

import io.redbee.socialmedia.entities.Interest;
import io.redbee.socialmedia.entities.Post;
import io.redbee.socialmedia.providers.Provider;
import io.redbee.socialmedia.services.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InterestServiceImpl implements InterestService {

    @Autowired
    @Qualifier("twitterProvider")
    private Provider provider;


    @Override
    public List<Post> queryInterestPosts(Interest interest) {

        List<Post> posts = provider.query(interest.getInterest(), interest.getLastIdQueried());

        interest.setLastTimeQueried(new Date());

        if(posts.size() > 0) {
            interest.addPosts(posts);
        }

        return posts;
    }

    @Override
    public Interest queryInterest(String interest) {

        // TODO: find interest in database
        Interest qInterest = new Interest(interest);
        queryInterestPosts(qInterest);

        return qInterest;
    }


}
