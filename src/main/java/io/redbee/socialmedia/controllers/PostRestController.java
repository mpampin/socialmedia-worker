package io.redbee.socialmedia.controllers;

import io.redbee.socialmedia.entities.Interest;
import io.redbee.socialmedia.entities.Post;
import io.redbee.socialmedia.services.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostRestController {

    private InterestService interestService;

    @Autowired
    public PostRestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @RequestMapping
    public List<Post> getPosts(@RequestParam("interest") String query) {
        Interest interest = this.interestService.queryInterest(query);
        return interest.getPosts();
    }

}
