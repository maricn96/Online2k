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

import com.online.calc.dto.FoodDTO;
import com.online.calc.model.Food;
import com.online.calc.service.FoodService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/food")
@Api(value = "Endpoints for food")
public class FoodController {
	
	private final FoodService foodService;
	
	public FoodController(FoodService foodService) {
		this.foodService = foodService;
	}
	
	@ApiOperation(value = "Get food by id.")
	@ApiResponse(code = 200, message = "Successfully retrieved.")
	@GetMapping("/{id}")
	public ResponseEntity<FoodDTO> getOne(@PathVariable("id") String id) {
		Food retVal = foodService.getOne(new ObjectId(id));
		
		return (retVal != null) ? new ResponseEntity<FoodDTO>(retVal.asDto(), HttpStatus.OK) : new ResponseEntity<FoodDTO>(new FoodDTO(), HttpStatus.NOT_FOUND); 
	}
	
	@ApiOperation(value = "Get all food.")
	@ApiResponse(code = 200, message = "Successfully retrieved.")
	@GetMapping
	public ResponseEntity<List<FoodDTO>> getAll() {
		List<Food> retVal = foodService.getAll();
		List<FoodDTO> retDto = new ArrayList<FoodDTO>();
		
		for(Food food : retVal) {
			retDto.add(food.asDto());
		}
		
		return (!retDto.isEmpty()) ? new ResponseEntity<List<FoodDTO>>(retDto, HttpStatus.OK) : new ResponseEntity<List<FoodDTO>>(new ArrayList<FoodDTO>(), HttpStatus.NOT_FOUND); 
	}
	
	@ApiOperation(value = "Create food.")
	@ApiResponse(code = 200, message = "Successfully created food.")
	@PostMapping
	public ResponseEntity<FoodDTO> create(@RequestBody FoodDTO foodDto) {
		System.out.println(foodDto);
		Food retVal = foodService.create(foodDto);
		
		return (retVal != null) ? new ResponseEntity<FoodDTO>(new FoodDTO(retVal), HttpStatus.CREATED) : new ResponseEntity<FoodDTO>(new FoodDTO(), HttpStatus.BAD_REQUEST); 
	}
	
	@ApiOperation(value = "Update food.")
	@ApiResponse(code = 200, message = "Successfully updated food.")
	@PutMapping("/{id}")
	public ResponseEntity<FoodDTO> update(@PathVariable("id") ObjectId id, @RequestBody FoodDTO foodDto) {
		Food retVal = foodService.update(id, foodDto);
		
		return (retVal != null) ? new ResponseEntity<FoodDTO>(new FoodDTO(retVal), HttpStatus.CREATED) : new ResponseEntity<FoodDTO>(new FoodDTO(), HttpStatus.BAD_REQUEST); 
	}
	
	@ApiOperation(value = "Delete food.")
	@ApiResponse(code = 200, message = "Successfully deleted food.")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") ObjectId id) {
		foodService.delete(id);
		return new ResponseEntity<String>("DELETED", HttpStatus.OK);
	}
	
	
	
	/*
	 * OSTALE OPERACIJE
	 */
	
	
	
	@ApiOperation(value = "Get all food names.")
	@ApiResponse(code = 200, message = "Successfully retrieved.")
	@GetMapping("/names")
	public ResponseEntity<List<String>> getAllNames() {
		List<String> retVal = foodService.getAllNames();
		
		return (!retVal.isEmpty()) ? new ResponseEntity<List<String>>(retVal, HttpStatus.OK) : new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.NOT_FOUND); 
	}
}
