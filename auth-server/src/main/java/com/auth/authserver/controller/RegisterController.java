package com.auth.authserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.authserver.dto.UserDTO;
import com.auth.authserver.dto.UserToRegDTO;
import com.auth.authserver.service.impl.LoginServiceImpl;
import com.auth.authserver.service.impl.RegisterServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/register")
@Api(value = "Endpoint for user registration.")
public class RegisterController {

	private final RegisterServiceImpl registerService;
	
	public RegisterController(RegisterServiceImpl registerService) {
		this.registerService = registerService;
	}
	/*
	 * Registracija korisnika (unosi podatke u bazu sa statusom NEAKTIVAN)
	 * Neaktivan je sve dok ne potvrdi registraciju preko mail-a
	 */
	@ApiOperation(value = "Registration by user.")
	@ApiResponse(code = 201, message = "Successfully registered.")
	@PostMapping
	public ResponseEntity<String> register(@RequestBody UserToRegDTO userDto) {
		System.out.println("register()");
		String isReg = registerService.register(userDto);
		return (!isReg.equals("REGISTERED")) ? new ResponseEntity<String>(isReg, HttpStatus.BAD_REQUEST) : new ResponseEntity<String>(isReg, HttpStatus.CREATED); 
	}
	
}
