package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class UserDetailsService extends WebSecurityConfigurerAdapter{

	@Autowired
	private org.springframework.security.core.userdetails.UserDetailsService userservice;
	
	@Autowired
	private PasswordEncoder passencoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userservice).passwordEncoder(passencoder);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests()
		.antMatchers("/saveuser").permitAll()
		.antMatchers("/getproducts").hasAuthority("admin")
		.anyRequest().hasAuthority("admin")
		.and()
		.httpBasic()
		.and()
		.csrf().disable();
		
	}
}
