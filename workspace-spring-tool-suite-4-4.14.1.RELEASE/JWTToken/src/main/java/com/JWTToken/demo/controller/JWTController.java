package com.JWTToken.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JWTToken.demo.model.JWTRequest;
import com.JWTToken.demo.model.JWTResponse;
import com.JWTToken.demo.model.JWTUtil;
import com.JWTToken.demo.security.CustomUserServiceDetails;
import com.JWTToken.demo.security.LoginUserdetailsService;

@RestController
public class JWTController {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private CustomUserServiceDetails serviceDetails;
	
	@Autowired
	private LoginUserdetailsService loginservice;
	
	@Autowired
	private AuthenticationManager authManager;

	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtrequest) throws Exception{
		System.out.println(jwtrequest);
		try {
			this.authManager.authenticate(new UsernamePasswordAuthenticationToken(jwtrequest.getUsername(), jwtrequest.getPassword()));
			
		}catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Bad Credentials");
		}
		
		UserDetails userDetails=this.loginservice.loadUserByUsername(jwtrequest.getUsername());
		
		String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JWTResponse(token));
	}
}
