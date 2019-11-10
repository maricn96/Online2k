package com.online.calc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import com.online.calc.dto.CalculationDTO;

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
public class Calculation {
	//reprezentuje ukupnu kolicinu po makronutrijentima za datum i korisnika
	@Id
	private ObjectId id;
	private Set<ObjectId> meals = new HashSet<>();
	private Date dateOfCalculation;
	private ObjectId user;
	private Boolean downloaded = false;
	
	
	public Calculation(Set<ObjectId> meals, Date dateOfCalculation, ObjectId user, Boolean downloaded) {
		Assert.notNull(meals, "Meals must not be empty!");
		Assert.notNull(dateOfCalculation, "Date of calculation must not be empty!");
		Assert.notNull(user, "User fk must not be empty!");
		this.meals = meals;
		this.dateOfCalculation = dateOfCalculation;
		this.user = user;
		this.downloaded = downloaded;
	}
	
	public Calculation(ObjectId id, Set<ObjectId> meals, Date dateOfCalculation, ObjectId user, Boolean downloaded) {
		this(meals, dateOfCalculation, user, downloaded);
		Assert.notNull(id, "Id must not be empty!");
		this.id = id;
	}
	
	public Calculation(CalculationDTO dto) {
		if(dto.getId() != null) 
			this.id = new ObjectId(dto.getId());
		
		for(String meal : dto.getMeals()) {
			ObjectId mealid = new ObjectId(meal);
			this.meals.add(mealid);
		}
		this.dateOfCalculation = dto.getDateOfCalculation();
		this.user = new ObjectId(dto.getUser());
		this.downloaded = dto.isDownloaded();
	}
	
	public CalculationDTO asDto() {
		return new CalculationDTO(this);
	}
	
}
