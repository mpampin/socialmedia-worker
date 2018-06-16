package io.redbee.socialmedia.entities;

import java.util.Set;

public class Board {

    private Set<Interest> interests;

    public Set<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
    }

    public void addInterests(Set<Interest> newInterests) {
        this.interests.addAll(newInterests);
    }
}
