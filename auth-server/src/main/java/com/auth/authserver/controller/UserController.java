package com.auth.authserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.authserver.dto.UserDTO;
import com.auth.authserver.service.UserService;
import com.auth.authserver.service.impl.LoginServiceImpl;
import com.auth.authserver.service.impl.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
@Api(value = "Endpoint for user operations.")
public class UserController {

private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@ApiOperation(value = "Retrieve all users.")
	@ApiResponse(code = 200, message = "Successfully retrieved.")
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> retVal = userService.getAllUsers();
		return (retVal.isEmpty()) ? new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND) : new ResponseEntity<List<UserDTO>>(retVal, HttpStatus.OK); 
	}
	
	
}
