package io.redbee.socialmedia.providers;

import io.redbee.socialmedia.entities.Post;

import java.util.Date;
import java.util.List;

public interface Provider {

    List<Post> query(String interest, Long lastId);

}
