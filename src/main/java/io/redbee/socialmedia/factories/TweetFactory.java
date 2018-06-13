package io.redbee.socialmedia.factories;

import io.redbee.socialmedia.entities.Tweet;
import twitter4j.HashtagEntity;
import twitter4j.User;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class TweetFactory {

    private Tweet tweet;

    public TweetFactory() {
        this.tweet = new Tweet();
    }

    public TweetFactory withUser(User user) {
        tweet.setUser(user.getName());
        return this;
    }

    public TweetFactory withMessage(String message) {
        tweet.setMessage(message);
        return this;
    }

    public TweetFactory withHashtags(HashtagEntity[] hashtags) {

        Set<String> mappedHashtags = Arrays.stream(hashtags)
                .map(HashtagEntity::getText)
                .collect(Collectors.toSet());;

        tweet.setHashtags(mappedHashtags);
        return this;
    }

    public Tweet build() {
        return tweet;
    }
}
