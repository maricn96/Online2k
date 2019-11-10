package com.online.calc.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CalculationToShowDTO {

	private String calculation; //calculation id
	private Date dateOfCalculation;
	private double calories;
	private double proteins;
	private double carbs;
	private double fat;
	
	
	
}
