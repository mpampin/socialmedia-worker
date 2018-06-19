package io.redbee.socialmedia.repositories;

import io.redbee.socialmedia.entities.Interest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InterestRepository extends MongoRepository<Interest, String> {

    @Cacheable("interest")
    Interest findInterestByQuery(String query);
}
