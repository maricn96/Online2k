package com.online.calc.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.online.calc.model.Calculation;

public interface CalculationRepository extends MongoRepository<Calculation, ObjectId>{
	
	List<Calculation> findByUser(ObjectId user);
}
