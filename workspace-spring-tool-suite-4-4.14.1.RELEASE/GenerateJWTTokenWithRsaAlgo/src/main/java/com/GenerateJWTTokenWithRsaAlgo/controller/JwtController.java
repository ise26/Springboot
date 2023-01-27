package com.GenerateJWTTokenWithRsaAlgo.controller;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GenerateJWTTokenWithRsaAlgo.service.JwtService;

@RestController
public class JwtController {

	@Autowired
	private JwtService service;
	
	@GetMapping("create")
	public String createToken() throws NoSuchAlgorithmException, InvalidKeySpecException {
		return service.getValue();
	}
}
