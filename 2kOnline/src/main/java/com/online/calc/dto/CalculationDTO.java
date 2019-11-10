package com.online.calc.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;

import com.online.calc.model.Calculation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@ApiModel(description = "DTO object that represents food amount bean.")
@NoArgsConstructor
public class CalculationDTO {
	
	@ApiModelProperty(notes = "Generated id.")
	private String id;
	
	//omg nemam podatak koliki je amount po svakom meal-u
	private Set<String> meals = new HashSet<>(); //id meal-eva, preko njih cemo moci detaljisati svaku kalkulaciju kad korisnik zatrazi
	
	@NotBlank
	private Date dateOfCalculation;
	
	@NotBlank
	private String user;
	
	private boolean downloaded;
	
	
	public CalculationDTO(ObjectId id, Set<String> meals, Date dateOfCalculation, String user, boolean downloaded) {
		init(meals, dateOfCalculation, user, downloaded);
		this.id = id.toString();
	}
	
	public CalculationDTO(Calculation calculation) {
		this.id = calculation.getId().toString();
		for(ObjectId meal : calculation.getMeals()) {
			String mealid = meal.toString();
			this.meals.add(mealid);
		}
		this.dateOfCalculation = calculation.getDateOfCalculation();
		this.user = calculation.getUser().toString();
		this.downloaded = calculation.getDownloaded();
	}
	
	public void init(Set<String> meals, Date dateOfCalculation, String user, boolean downloaded) {
		this.meals = meals;
		this.dateOfCalculation = dateOfCalculation;
		this.user = user;
		this.downloaded = downloaded;
	}
	
	public Calculation asBean() {
		return new Calculation(this);
	}
	
}
