package com.online.calc.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import com.online.calc.dto.MealDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Document
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Meal {
	
	@Id
	private ObjectId id;
	private Date dateOfMeal;
	private ObjectId user;
	private Double amount;
	private String food; //ime namirnice
	private Boolean saved; //ako je sacuvan nece se load
	
	public Meal(Date dateOfMeal, ObjectId user, Double amount, String food, Boolean saved) {
//		Assert.notNull(dateOfMeal, "Date of meal must not be empty!");
		Assert.notNull(user, "User must not be empty!");
		Assert.notNull(amount, "Amount must not be empty!");
		Assert.notNull(food, "Food must not be empty!");
		Assert.notNull(saved, "Saved must not be null!");
		this.dateOfMeal = dateOfMeal;
		this.user = user;
		this.amount = amount;
		this.food = food;
		this.saved = saved;
	}
	
	public Meal(ObjectId id, Date dateOfMeal, ObjectId user, Double amount, String food, Boolean saved) {
		this(dateOfMeal, user, amount, food, saved);
		Assert.notNull(id, "Id must not be empty!");
		this.id = id;
	}
	
	public Meal(MealDTO dto) {
		if(dto.getId() != null) 
			this.id = new ObjectId(dto.getId());
		
		this.dateOfMeal = dto.getDateOfMeal();
		this.user = new ObjectId(dto.getUser());
		this.amount = dto.getAmount();
		this.food = dto.getFood();
	}
	
	public MealDTO asDto() {
		return new MealDTO(this);
	}
	

}
