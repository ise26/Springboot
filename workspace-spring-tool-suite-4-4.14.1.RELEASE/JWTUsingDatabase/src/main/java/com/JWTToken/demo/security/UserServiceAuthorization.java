package com.JWTToken.demo.security;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.JWTToken.demo.jwt.JWTFilter;
import com.JWTToken.demo.serviceImpl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class UserServiceAuthorization extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImpl userAutorization;
	
	@Autowired
	private JWTFilter jwtFilter;

	@Autowired
	private PasswordEncoder passEncode;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userAutorization).passwordEncoder(passEncode);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/email").hasAuthority("employee")
		.antMatchers("/add").hasAnyAuthority("customer","admin")
		.antMatchers("/token").permitAll()
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 .and()		.csrf().disable().httpBasic();
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	@Bean
	public AuthenticationManager authrnticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
