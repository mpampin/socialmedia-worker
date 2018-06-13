package io.redbee.socialmedia.exceptions;

import twitter4j.TwitterException;

public class TweetNotFoundException extends RuntimeException {
    public TweetNotFoundException(TwitterException e) {
        super(e);
    }
}
