package com.online.calc.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.online.calc.dto.CalculationDTO;
import com.online.calc.dto.CalculationToShowDTO;
import com.online.calc.dto.LoadMealsDTO;
import com.online.calc.dto.MealDTO;
import com.online.calc.model.Calculation;
import com.online.calc.model.Meal;

public interface CalculationService {
	
	Calculation getOneById(String id);
	
	Meal getOneMealById(ObjectId id);

	Boolean create(CalculationDTO calculationDto);
	
	double round(double value, int places);

	List<CalculationToShowDTO> getAllByUser(String userid);

	Boolean createCurrentCalculation(MealDTO mealDto);

	LoadMealsDTO loadMeals(String userid);

	List<MealDTO> getAllUnsavedMeals(String userid);

	List<MealDTO> getAllMealsByCalc(String id);

	Boolean deleteCalculationById(String id);
	
	List<CalculationDTO> getAllCalculations();

	

	

}
