package io.redbee.socialmedia.services.impl;

import io.redbee.socialmedia.entities.Post;
import io.redbee.socialmedia.services.PostPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class PostPublishServiceImpl implements PostPublishService {

    private RestTemplate client;

    @Value("${io.redbee.socialmedia.publishServer.url}")
    private String postPublishUrl;

    @Autowired
    public PostPublishServiceImpl(RestTemplateBuilder restTemplateBuilder) {

        client = restTemplateBuilder
                .build();

    }

    @Override
    public void publish(List<Post> posts) {
        client.put(postPublishUrl, posts);
    }

}
