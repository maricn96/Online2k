package com.online.calc.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.online.calc.dto.FoodDTO;
import com.online.calc.model.Food;

public interface FoodService {
	
	Food getOne(ObjectId id);
	
	Food getOneByName(String name);

	List<Food> getAll();
	
	Food create(FoodDTO foodDto);

	Food update(ObjectId id, FoodDTO foodDto);

	void delete(ObjectId id);

	List<String> getAllNames();

	

	
	
}
