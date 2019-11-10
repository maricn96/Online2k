package com.online.calc.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.online.calc.dto.FoodDTO;
import com.online.calc.model.Food;
import com.online.calc.repository.FoodRepository;
import com.online.calc.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	private static final Logger log = LogManager.getLogger(FoodServiceImpl.class);
	
	private FoodRepository foodRepository;
	
	public FoodServiceImpl(FoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}
	
	
	@Override
	public Food getOne(ObjectId id) {
		log.info("FoodService GET ONE by id %s", id);
		return foodRepository.findById(id).get();
	}
	
	public Food getOneByName(String name) {
		return foodRepository.findByName(name);
	}
	
	@Override
	public List<Food> getAll() {
		log.info("FoodService GET ALL");
		return foodRepository.findAll();
	}
	
	@Override
	public Food create(FoodDTO foodDto) {
		log.info("FoodService CREATE %s", foodDto);
		return foodRepository.save(new Food(foodDto));
	}


	@Override
	public Food update(ObjectId id, FoodDTO foodDto) {
		log.info("FoodService UPDATE with id %s", id);
		Optional<Food> food = foodRepository.findById(id);
		if(!food.isPresent())		{
			//baci neki eksepsn
		}
		return foodRepository.save(foodDto.asBean());
	}


	@Override
	public void delete(ObjectId id) {
		log.info("FoodService DELETE with id %s", id);
		foodRepository.deleteById(id);
	}


	@Override
	public List<String> getAllNames() {
		List<Food> allFood = foodRepository.findAll();
		List<String> allFoodNames = new ArrayList<String>();
		
		for(Food food : allFood) 
			allFoodNames.add(food.getName());
		
		Collections.sort(allFoodNames);
		
		return allFoodNames;
	}

	



}
