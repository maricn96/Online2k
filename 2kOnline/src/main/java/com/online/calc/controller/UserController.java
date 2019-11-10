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
import com.online.calc.dto.UserDTO;
import com.online.calc.model.Food;
import com.online.calc.model.User;
import com.online.calc.service.FoodService;
import com.online.calc.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
@Api(value = "Endpoints for user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@ApiOperation(value = "Get user by id.")
	@ApiResponse(code = 200, message = "Successfully retrieved.")
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getOne(@PathVariable("id") String id) {
		User retVal = userService.getOne(new ObjectId(id));
		
		return (retVal != null) ? new ResponseEntity<UserDTO>(retVal.asDto(), HttpStatus.OK) : new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.NOT_FOUND); 
	}
	
	@ApiOperation(value = "Get user by username.")
	@ApiResponse(code = 200, message = "Successfully retrieved.")
	@GetMapping("/getbyusername/{username}")
	public ResponseEntity<UserDTO> getOneByUsername(@PathVariable("username") String username) {
		User retVal = userService.getOneByUsername(username);
		
		return (retVal != null) ? new ResponseEntity<UserDTO>(retVal.asDto(), HttpStatus.OK) : new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.NOT_FOUND); 
	}
	
	@ApiOperation(value = "Get all users.")
	@ApiResponse(code = 200, message = "Successfully retrieved.")
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAll() {
		List<User> retVal = userService.getAll();
		List<UserDTO> retDto = new ArrayList<UserDTO>();
		
		for(User user : retVal) {
			retDto.add(user.asDto());
		}
		
		return (!retDto.isEmpty()) ? new ResponseEntity<List<UserDTO>>(retDto, HttpStatus.OK) : new ResponseEntity<List<UserDTO>>(new ArrayList<UserDTO>(), HttpStatus.NOT_FOUND); 
	}
	
	@ApiOperation(value = "Create user.")
	@ApiResponse(code = 200, message = "Successfully created user.")
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDto) {
		User retVal = userService.create(userDto);
		
		return (retVal != null) ? new ResponseEntity<UserDTO>(new UserDTO(retVal), HttpStatus.CREATED) : new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.BAD_REQUEST); 
	}
	
	@ApiOperation(value = "Update user.")
	@ApiResponse(code = 200, message = "Successfully updated user.")
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable("id") ObjectId id, @RequestBody UserDTO userDto) {
		User retVal = userService.update(id, userDto);
		
		return (retVal != null) ? new ResponseEntity<UserDTO>(new UserDTO(retVal), HttpStatus.CREATED) : new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.BAD_REQUEST); 
	}
	
	@ApiOperation(value = "Delete user.")
	@ApiResponse(code = 200, message = "Successfully deleted user.")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") ObjectId id) {
		userService.delete(id);
		return new ResponseEntity<String>("DELETED", HttpStatus.OK);
	}

}
