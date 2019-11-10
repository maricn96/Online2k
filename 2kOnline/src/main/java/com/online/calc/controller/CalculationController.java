package com.online.calc.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.online.calc.dto.CalculationDTO;
import com.online.calc.dto.CalculationToShowDTO;
import com.online.calc.dto.FoodDTO;
import com.online.calc.dto.LoadMealsDTO;
import com.online.calc.dto.MealDTO;
import com.online.calc.model.Calculation;
import com.online.calc.model.Food;
import com.online.calc.service.CalculationService;
import com.online.calc.service.FoodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/calculation")
@Api(value = "Endpoints for user calculations, history of calculations and meals.")
public class CalculationController {
	
	private final CalculationService calculationService;
	
	public CalculationController(CalculationService calculationService) {
		this.calculationService = calculationService;
	}
	
	
	@ApiOperation(value = "Create new calculation for one day by specific user.")
	@ApiResponse(code = 201, message = "Successfully created calculation.")
	@PostMapping
	public ResponseEntity<Boolean> create(@RequestBody CalculationDTO calculationDto) {
		System.out.println(calculationDto);
		return (!calculationService.create(calculationDto)) ? new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST) : new ResponseEntity<Boolean>(true, HttpStatus.CREATED); 
	}
	
	//da li je pametno slati id iz baze u headeru?
	@ApiOperation(value = "Retrieve all history calculations by specific user.")
	@ApiResponse(code = 200, message = "Successfully retrieved.")
	@GetMapping("/allcalcsbyuser/{userid}")
	public ResponseEntity<List<CalculationToShowDTO>> getAllByUser(@PathVariable("userid") String userid){
		List<CalculationToShowDTO> retVal = calculationService.getAllByUser(userid);
		return (retVal.isEmpty()) ? new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND) : new ResponseEntity<List<CalculationToShowDTO>>(retVal, HttpStatus.OK); 
	}
	
	@ApiOperation(value = "Create new calculation for one day by specific user.")
	@ApiResponse(code = 201, message = "Successfully created calculation.")
	@PostMapping("/currentcalc")
	public ResponseEntity<Boolean> createCurrentCalculation(@RequestBody MealDTO mealDto) {
		return (!calculationService.createCurrentCalculation(mealDto)) ? new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST) : new ResponseEntity<Boolean>(true, HttpStatus.CREATED); 
	}
	
	@ApiOperation(value = "Retrieve macronutrients that are not saved by user.")
	@ApiResponse(code = 200, message = "Successfully retrieved.")
	@GetMapping("/load/{userid}")
	public ResponseEntity<LoadMealsDTO> loadMeals(@PathVariable("userid") String userid){
		LoadMealsDTO retVal = calculationService.loadMeals(userid);
		return (retVal == null) ? new ResponseEntity<>(new LoadMealsDTO(), HttpStatus.NOT_FOUND) : new ResponseEntity<LoadMealsDTO>(retVal, HttpStatus.OK); 
	}
	
	@ApiOperation(value = "Retrieve all unsaved meals by user.")
	@ApiResponse(code = 200, message = "Successfully retrieved.")
	@GetMapping("/allunsavedmeals/{userid}")
	public ResponseEntity<List<MealDTO>> getAllUnsavedMeals(@PathVariable("userid") String userid){
		List<MealDTO> retVal = calculationService.getAllUnsavedMeals(userid);
		return (retVal.isEmpty()) ? new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND) : new ResponseEntity<List<MealDTO>>(retVal, HttpStatus.OK); 
	}
	
	@ApiOperation(value = "Retrieve all meals by calculation id.")
	@ApiResponse(code = 200, message = "Successfully retrieved.")
	@GetMapping("/allmealsbycalc/{calculationid}")
	public ResponseEntity<List<MealDTO>> getAllMealsByCalc(@PathVariable("calculationid") String id){
		List<MealDTO> retVal = calculationService.getAllMealsByCalc(id);
		return (retVal.isEmpty()) ? new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND) : new ResponseEntity<List<MealDTO>>(retVal, HttpStatus.OK); 
	}
	
	@ApiOperation(value = "Delete one calculation by calculation id.")
	@ApiResponse(code = 200, message = "Successfully deleted.")
	@DeleteMapping("/{calculationid}")
	public ResponseEntity<Boolean> deleteCalculationById(@PathVariable("calculationid") String id){
		
		return (!calculationService.deleteCalculationById(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST) : new ResponseEntity<Boolean>(true, HttpStatus.OK); 
	}
	
}
