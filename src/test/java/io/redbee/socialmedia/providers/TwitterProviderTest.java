package io.redbee.socialmedia.providers;

import io.redbee.socialmedia.entities.Interest;
import io.redbee.socialmedia.entities.Post;
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

        Post post = twitterProvider.getATweet();
        Assert.assertNotNull(post);

    }

    @Test
    public void getTweetsForAHashtag() {

        Interest interest = new Interest("#StarWars");
        List<Post> posts = twitterProvider.queryUpdates(interest);
        Assert.assertTrue(posts.size() > 0);

    }

}
