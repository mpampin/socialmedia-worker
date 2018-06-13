package io.redbee.socialmedia.controllers;

import io.redbee.socialmedia.entities.Post;
import io.redbee.socialmedia.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostRestController {

    private PostService postService;

    @Autowired
    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping
    public List<Post> getPosts(@RequestParam("interest") String interest) {
        return this.postService.getPostsForInterest(interest);
    }

}
