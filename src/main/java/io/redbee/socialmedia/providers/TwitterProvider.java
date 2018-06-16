package io.redbee.socialmedia.providers;

import io.redbee.socialmedia.entities.Interest;
import io.redbee.socialmedia.entities.Post;
import io.redbee.socialmedia.exceptions.TweetNotFoundException;
import io.redbee.socialmedia.factories.PostFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("twitterProvider")
public class TwitterProvider implements Provider {

    public Post getATweet() {

        return getTweets("#askNASA", null).get(0);

    }

    private List<Post> parseTweets(List<Status> tweets) {

        return tweets.stream()
                .map(status -> {
                    return new PostFactory()
                            .withId(status.getId())
                            .withUser(status.getUser())
                            .withMessage(status.getText())
                            .withHashtags(status.getHashtagEntities())
                            .build();
                })
                .collect(Collectors.toList());

    }


    @Override
    public List<Post> queryUpdates(Interest interest) {
        return this.getTweets(interest.getQuery(), interest.getLastIdQueried());
    }

    private List<Post> getTweets(String interest, Long lastId) {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(interest);

        if(lastId != null) query.sinceId(lastId);

        QueryResult result = null;

        try {
            result = twitter
                    .search(query);
        } catch (TwitterException e) {
            throw new TweetNotFoundException(e);
        }

        return this.parseTweets(result.getTweets());
    }
}
