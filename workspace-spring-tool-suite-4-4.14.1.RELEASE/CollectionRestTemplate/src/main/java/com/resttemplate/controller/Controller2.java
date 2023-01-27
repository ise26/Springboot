package com.resttemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resttemplate.service.ResttemplateServiceImpl;

@RestController
public class Controller2 {

	@Autowired
	private ResttemplateServiceImpl service;
	
	@PostMapping("getkey")
	public void generateKey() {
		service.generateSecretKey();
	}
	
}
