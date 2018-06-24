package io.redbee.socialmedia.workers;

import io.redbee.socialmedia.entities.Interest;
import io.redbee.socialmedia.entities.Post;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface InterestsUpdater {

    void updateAllInterests() throws ExecutionException, InterruptedException;
}
