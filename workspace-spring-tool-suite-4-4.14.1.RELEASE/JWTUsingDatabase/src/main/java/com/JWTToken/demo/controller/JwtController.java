package com.JWTToken.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JWTToken.demo.entity.User;
import com.JWTToken.demo.jwt.JWTResponse;
import com.JWTToken.demo.jwt.JWTUtil;
import com.JWTToken.demo.security.UserServiceAuthorization;
import com.JWTToken.demo.serviceImpl.UserServiceImpl;

@RestController
public class JwtController {

	@Autowired
	private UserServiceImpl loadByUsernameClass;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UserServiceAuthorization authorizationClass;
	@Autowired
	private AuthenticationManager authManager;
	
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody User user) throws Exception{
		
		try {
			this.authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		}catch (Exception e) {
			throw new UsernameNotFoundException("User not found");
		}
		
		UserDetails userDetails = this.loadByUsernameClass.loadUserByUsername(user.getEmail());
		String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JWTResponse(token));
	}
}
