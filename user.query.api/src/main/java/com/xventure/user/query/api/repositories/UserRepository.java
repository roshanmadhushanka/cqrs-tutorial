package com.xventure.user.query.api.repositories;

import com.xventure.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'$or' : [{'firstName': {$regex : ?0, $options: '1'}}, {'lastName': {$regex : ?0, $options: '1'}}, {'emailAddress': {$regex : ?0, $options: '1'}}, {'account.userName': {$regex : ?0, $options: '1'}}]}")
    List<User> findByFilterRegex(String filter);
}
