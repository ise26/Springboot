package com.JWTToken.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JWTToken.demo.entity.User;
import com.JWTToken.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PostMapping("/email")
	public User getEmail(@RequestBody User user) {
		return userService.getemail(user);
	}
}
