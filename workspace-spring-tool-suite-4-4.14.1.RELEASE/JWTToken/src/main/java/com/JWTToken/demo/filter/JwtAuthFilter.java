package com.JWTToken.demo.filter;

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

import com.JWTToken.demo.model.JWTUtil;
import com.JWTToken.demo.security.CustomUserServiceDetails;
import com.JWTToken.demo.security.LoginUserdetailsService;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private LoginUserdetailsService loginService;
		
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		String header=request.getHeader("Authorization");
		
		String username=null;
		String jwtToken=null;
		
		if(header != null && header.startsWith("Bearer ")) {
			jwtToken=header.substring(7);
			System.out.println(jwtToken);
			try {
				username = this.jwtUtil.extractUsername(jwtToken);
				System.out.println(username);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			UserDetails userDetails= this.loginService.loadUserByUsername(username);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				System.out.println(username);
				UsernamePasswordAuthenticationToken usernamepasstoken =new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				usernamepasstoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamepasstoken);
			}
			
		}
		filterChain.doFilter(request,response);
	}
}
