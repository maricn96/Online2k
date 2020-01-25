package com.auth.authserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.authserver.service.impl.LoginServiceImpl;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
@Api(value = "Endpoint for user login.")
public class LoginController {

	private final LoginServiceImpl loginService;
	
	public LoginController(LoginServiceImpl loginService) {
		this.loginService = loginService;
	}
	
	
	
}
