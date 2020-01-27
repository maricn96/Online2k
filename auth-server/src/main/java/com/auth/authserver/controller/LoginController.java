package com.auth.authserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.authserver.dto.UserToLoginDTO;
import com.auth.authserver.dto.UserToRegDTO;
import com.auth.authserver.service.impl.LoginServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
@Api(value = "Endpoint for user login.")
public class LoginController {

	private final LoginServiceImpl loginService;
	
	public LoginController(LoginServiceImpl loginService) {
		this.loginService = loginService;
	}
	
	/*
	 * Login korisnika
	 */
	@ApiOperation(value = "Login by user.")
	@ApiResponse(code = 201, message = "Successfully signed in.")
	@PostMapping
	public ResponseEntity<String> login(@RequestBody UserToLoginDTO userDto) {
		System.out.println("login()");
		String isLog = loginService.login(userDto);
		return (isLog.equals("UNKNOWN_ERR") || isLog.equals("NOT_FOUND_ERR") || isLog.equals("NOT_ACTIVE_ERR")) ? new ResponseEntity<String>(isLog, HttpStatus.BAD_REQUEST) : new ResponseEntity<String>(isLog, HttpStatus.OK); 
	}
	
	
}
