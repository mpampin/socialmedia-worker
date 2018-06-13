package io.redbee.socialmedia.providers;

import io.redbee.socialmedia.entities.Post;
import io.redbee.socialmedia.exceptions.TweetNotFoundException;
import io.redbee.socialmedia.factories.PostFactory;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TwitterProvider {

    public Post getATweet() {

        return getTweets("#askNASA").get(0);

    }

    public List<Post> getTweets(String interest) {

        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(interest);
        QueryResult result = null;

        try {
            result = twitter.search(query);
        } catch (TwitterException e) {
            throw new TweetNotFoundException(e);
        }

        return this.parseTweets(result.getTweets());

    }

    private List<Post> parseTweets(List<Status> tweets) {

        return tweets.stream()
                .map(status -> {
                    return new PostFactory()
                            .withUser(status.getUser())
                            .withMessage(status.getText())
                            .withHashtags(status.getHashtagEntities())
                            .build();
                })
                .collect(Collectors.toList());

    }


}
