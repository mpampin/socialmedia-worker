package io.redbee.socialmedia.services;

import io.redbee.socialmedia.entities.Interest;
import io.redbee.socialmedia.entities.Post;
import io.redbee.socialmedia.providers.Provider;
import io.redbee.socialmedia.repositories.InterestRepository;
import io.redbee.socialmedia.services.impl.InterestServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class InterestServiceImplTest {

    @Mock
    private Provider provider;

    @Mock
    private InterestRepository repository;

    @InjectMocks
    private InterestServiceImpl interestService;

    @Before
    public void setUp() {

        List<Post> posts = new ArrayList<>();
        posts.add(createBasicPost(4l));
        posts.add(createBasicPost(3l));

        when(provider.queryUpdates("#StarWars", 0L))
                .thenReturn(posts);

    }

    @Test
    public void queryNewInterestTest() {

        when(repository.findInterestByQuery("#StarWars"))
                .thenReturn(null);

        Interest interest = interestService.queryInterest("#StarWars");

        Assert.assertNotNull(interest);
        Assert.assertEquals("#StarWars", interest.getQuery());
        Assert.assertEquals(Long.valueOf(4), interest.getLastIdQueried());
        Assert.assertNotNull(interest.getLastTimeQueried());
        Assert.assertNotNull(interest.getPosts());
        Assert.assertEquals(2, interest.getPosts().size());

    }

    @Test
    public void queryExistingInterestTest() {

        Interest existingInterest = new Interest("#StarWars");
        existingInterest.setLastTimeQueried(new Date());
        existingInterest.addPost(this.createBasicPost(0L));
        existingInterest.addPost(this.createBasicPost(-1L));

        when(repository.findInterestByQuery("#StarWars"))
                .thenReturn(existingInterest);

        Interest interest = interestService.queryInterest("#StarWars");

        Assert.assertNotNull(interest);
        Assert.assertEquals("#StarWars", interest.getQuery());
        Assert.assertEquals(Long.valueOf(4), interest.getLastIdQueried());
        Assert.assertNotNull(interest.getLastTimeQueried());
        Assert.assertNotNull(interest.getPosts());
        Assert.assertEquals(4, interest.getPosts().size());

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
