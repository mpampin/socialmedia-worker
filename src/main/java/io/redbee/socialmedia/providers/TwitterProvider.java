package io.redbee.socialmedia.providers;

import io.redbee.socialmedia.entities.Tweet;
import io.redbee.socialmedia.exceptions.TweetNotFoundException;
import io.redbee.socialmedia.factories.TweetFactory;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TwitterProvider {

    public Tweet getATweet() {

        return getTweets("#askNASA").get(0);

    }

    public List<Tweet> getTweets(String interest) {

        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(interest);
        QueryResult result = null;

        try {
            result = twitter.search(query);
        } catch (TwitterException e) {
            throw new TweetNotFoundException();
        }

        return this.parseTweets(result.getTweets());

    }

    private List<Tweet> parseTweets(List<Status> tweets) {

        return tweets.stream()
                .map(status -> {
                    return new TweetFactory()
                            .withUser(status.getUser())
                            .withMessage(status.getText())
                            .withHashtags(status.getHashtagEntities())
                            .build();
                })
                .collect(Collectors.toList());

    }


}
