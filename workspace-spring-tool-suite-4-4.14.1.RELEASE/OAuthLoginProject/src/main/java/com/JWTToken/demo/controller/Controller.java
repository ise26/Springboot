package com.JWTToken.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/home")
	public String home() {
		return "Welcome to OAuth Authentication Testing";
	}
	
	@GetMapping("/login")
	public String login() {
		return "Welcome user Authentication successfull";
	}
}
