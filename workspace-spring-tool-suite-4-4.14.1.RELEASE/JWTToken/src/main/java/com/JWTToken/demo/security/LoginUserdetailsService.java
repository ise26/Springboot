package com.JWTToken.demo.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserdetailsService implements UserDetailsService {

	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(username.equals("shubham")) {
			return new User("shubham","123", new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("user not found");
		}
	}

}
