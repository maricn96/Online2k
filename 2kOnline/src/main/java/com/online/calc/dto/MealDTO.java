package com.online.calc.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;

import com.online.calc.model.Food;
import com.online.calc.model.Meal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(description = "All details about Employee.")
public class MealDTO {
	
	@ApiModelProperty(notes = "Generated id.")
	private String id;
	
	@NotBlank
	private Date dateOfMeal;
	
	@NotBlank
	private String user;
	
	@NotBlank
	private double amount;
	
	@NotBlank
	private String food;
	
	@NotBlank
	private boolean saved;
	
	public MealDTO(ObjectId id, Date dateOfMeal, String user, double amount, String food, boolean saved) {
		init(dateOfMeal, user, amount, food, saved);
		this.id = id.toString();
	}
	
	public MealDTO(Meal meal) {
		this.id = meal.getId().toString();
		this.dateOfMeal = meal.getDateOfMeal();
		this.user = meal.getUser().toString();
		this.amount = meal.getAmount();
		this.food = meal.getFood();
		this.saved = meal.getSaved();
	}
	
	public void init(Date dateOfMeal, String user, double amount, String food, boolean saved) {
		this.dateOfMeal = dateOfMeal;
		this.user = user;
		this.amount = amount;
		this.food = food;
		this.saved = saved;
	}
	
	public Meal asBean() {
		return new Meal(this);
	}
	
	

}
