package io.redbee.socialmedia.services;

import io.redbee.socialmedia.entities.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    List<Post> getPostsForInterest(String interest);

}
