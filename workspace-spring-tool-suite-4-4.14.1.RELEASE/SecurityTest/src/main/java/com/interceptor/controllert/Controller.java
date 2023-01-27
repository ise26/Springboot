package com.interceptor.controllert;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/login")
	public String login() {
		System.out.println("hii");
		return "Test security";
	}
}
