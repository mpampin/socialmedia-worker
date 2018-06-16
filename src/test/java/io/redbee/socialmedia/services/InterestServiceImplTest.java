package io.redbee.socialmedia.services;

import io.redbee.socialmedia.entities.Interest;
import io.redbee.socialmedia.entities.Post;
import io.redbee.socialmedia.providers.Provider;
import io.redbee.socialmedia.services.impl.InterestServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class InterestServiceImplTest {

    @Mock
    private Provider provider;

    @InjectMocks
    private InterestServiceImpl interestService;

    @Before
    public void setUp() {

        List<Post> posts = new ArrayList<>();
        posts.add(createBasicPost(2l));
        posts.add(createBasicPost(1l));

        when(provider.query("#StarWars", 0L))
                .thenReturn(posts);

    }

    @Test
    public void queryNewInterestTest() {

        Interest interest = interestService.queryInterest("#StarWars");

        Assert.assertNotNull(interest);
        Assert.assertEquals("#StarWars", interest.getInterest());
        Assert.assertEquals(Long.valueOf(2), interest.getLastIdQueried());
        Assert.assertNotNull(interest.getLastTimeQueried());
        Assert.assertNotNull(interest.getPosts());
        Assert.assertEquals(2, interest.getPosts().size());

    }

    private Post createBasicPost(Long id) {
        Post post = new Post();
        post.setId(id);
        post.setUser("SomeUser");
        post.setMessage("I'm some user and this is my message");
        post.setHashtags(new HashSet<>());
        return post;
    }

}
