package com.online.calc.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.client.MongoDatabase;
import com.online.calc.model.Calculation;
import com.online.calc.model.Meal;

public interface MealRepository extends MongoRepository<Meal, ObjectId>{
	
	List<Meal> findByUser(ObjectId user);
}
