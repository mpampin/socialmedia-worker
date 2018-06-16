package io.redbee.socialmedia.providers;

import io.redbee.socialmedia.entities.Interest;
import io.redbee.socialmedia.entities.Post;

import java.util.List;

public interface Provider {

    List<Post> queryUpdates(Interest interest);

}
