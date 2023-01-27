package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.JwtRequest;
import com.example.demo.entity.JwtResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService{
	
	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  User user = userDao.findById(username).get();

	        if (user != null) {
	            return new org.springframework.security.core.userdetails.User(
	                    user.getUserName(),
	                    user.getUserPassword(),
	                    getAuthority(user)
	            );
	        } else {
	            throw new UsernameNotFoundException("User not found with username: " + username);
	        }
	}
	
	 public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
	        String userName = jwtRequest.getUserName();
	        String userPassword = jwtRequest.getUserPassword();
	        authenticate(userName, userPassword);

	        UserDetails userDetails = loadUserByUsername(userName);
	        String newGeneratedToken = jwtUtil.generateToken(userDetails);

	        User user = userDao.findById(userName).get();
	        return new JwtResponse(user, newGeneratedToken);
	    }
	 
	 private Set getAuthority(User user) {
	        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	        user.getRole().forEach(role -> {
	            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
	            System.out.println("roles are : " + role.getRoleName());
	        });
	        return authorities;
	    }
	 
	 private void authenticate(String userName, String userPassword) throws Exception {
	        try {
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
	        } catch (DisabledException e) {
	            throw new Exception("USER_DISABLED", e);
	        } catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);
	        }
	    }

}
