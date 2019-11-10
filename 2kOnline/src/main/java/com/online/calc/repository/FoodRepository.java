package com.online.calc.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.online.calc.model.Calculation;
import com.online.calc.model.Food;

public interface FoodRepository extends MongoRepository<Food, ObjectId> {
	
	Food findByName(String name);
}
