package com.JWTToken.demo.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.JWTToken.demo.security.UserServiceAuthorization;
import com.JWTToken.demo.serviceImpl.UserServiceImpl;

@Component
public class JWTFilter extends OncePerRequestFilter {
	@Autowired
	private UserServiceImpl loadByUsernameClass;
	@Autowired
	private JWTUtil jwtUtil;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		 String token=null;
		 String username=null;
		
		//getting token from header
		String header=request.getHeader("Authorization");
		 if(header != null && header.startsWith("Bearer")) {
			 token=header.substring(7);
			 System.out.println(token);
			 
			 
			 try {
				 username = jwtUtil.extractUsernameFromToken(token);
				 
			 }catch (Exception e) {
				e.printStackTrace();
			}
			 System.out.println("Username from token is "+username);
			 
			 //get authorization from userdetails
			 UserDetails userDetails =this.loadByUsernameClass.loadUserByUsername(username);
			 
			 //validate token
			 
			 if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				 
				 UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				 userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				 SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
			 }
			 
		 }
		 filterChain.doFilter(request, response);
		 
	}

}
