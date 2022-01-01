package com.xventure.user.query.api.repositories;

import com.xventure.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
