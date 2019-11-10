package com.online.calc.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;

import com.online.calc.model.Food;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@ApiModel(description = "DTO object that represents food bean.")
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
	
	@ApiModelProperty(notes = "Generated id.")
	private String id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private double calories;
	
	@NotBlank
	private double proteins;
	
	@NotBlank
	private double carbs;
	
	@NotBlank
	private double fat;
	
	public FoodDTO(ObjectId id, String name, double calories, double proteins, double carbs, double fat) {
		init(name, calories, proteins, carbs, fat);
		this.id = id.toString();
	}
	
	public FoodDTO(Food food) {
		this.id = food.getId().toString();
		this.name = food.getName();
		this.calories = food.getCalories();
		this.proteins = food.getProteins();
		this.carbs = food.getCarbs();
		this.fat = food.getFat();
	}
	
	public void init(String name, double calories, double proteins, double carbs, double fat) {
		this.name = name;
		this.calories = calories;
		this.proteins = proteins;
		this.carbs = carbs;
		this.fat = fat;
	}
	
	public Food asBean() {
		return new Food(this);
	}
	
}
