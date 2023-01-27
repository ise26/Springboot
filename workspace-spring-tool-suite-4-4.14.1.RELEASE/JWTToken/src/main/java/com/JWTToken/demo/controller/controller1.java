package com.JWTToken.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller1 {

	@GetMapping("/welcome")
	public String getMessage() {
		return "welcome user";
	}
	
}
