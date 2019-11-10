package com.online.calc.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoadMealsDTO {

	private double calories;
	private double proteins;
	private double carbs;
	private double fat;
	private Date dateOfCalculation;
}
