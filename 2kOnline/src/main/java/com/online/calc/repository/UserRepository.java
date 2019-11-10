package com.online.calc.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.online.calc.model.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{

	User findByUsername(String username);
	
}
