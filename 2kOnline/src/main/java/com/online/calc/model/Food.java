package com.online.calc.model;

import org.springframework.data.annotation.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import com.online.calc.dto.FoodDTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Document
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Food {
	
	@Id
	private ObjectId id;
	private String name;
	private Double calories;
	private Double proteins;
	private Double carbs;
	private Double fat;
	
	
	public Food(String name, Double calories, Double proteins, Double carbs, Double fat) {
		Assert.notNull(name, "Name must not be empty!");
		Assert.notNull(calories, "Calories must not be empty!");
		Assert.notNull(proteins, "Proteins must not be empty!");
		Assert.notNull(carbs, "Carbs must not be empty!");
		Assert.notNull(fat, "Fat must not be empty!");
		this.name = name;
		this.calories = calories;
		this.proteins = proteins;
		this.carbs = carbs;
		this.fat = fat;
	}
	
	public Food(ObjectId id, String name, Double calories, Double proteins, Double carbs, Double fat) {
		this(name, calories, proteins, carbs, fat);
		Assert.notNull(id, "Id must not be empty!");
		this.id = id;
	}
	
	public Food(FoodDTO dto) {
		if(dto.getId() != null) 
			this.id = new ObjectId(dto.getId());
		
		this.name = dto.getName();
		this.calories = dto.getCalories();
		this.proteins = dto.getProteins();
		this.carbs = dto.getCarbs();
		this.fat = dto.getFat();
	}
	
	public FoodDTO asDto() {
		return new FoodDTO(this);
	}
	
}
