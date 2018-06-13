package io.redbee.socialmedia.providers;

import io.redbee.socialmedia.entities.Tweet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterProviderTest {

    @Autowired
    private TwitterProvider twitterProvider;
    
    @Test
    public void getSingleTweet() {

        Tweet tweet = twitterProvider.getATweet();
        Assert.assertNotNull(tweet);

    }

    @Test
    public void getTweetsForAHashtag() {

        String hashtag = "#StarWars";
        List<Tweet> tweets = twitterProvider.getTweets(hashtag);
        Assert.assertTrue(tweets.size() > 0);

    }

}
