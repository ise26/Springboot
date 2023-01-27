package com.JWTToken.demo.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JWTToken.demo.entity.User;
import com.JWTToken.demo.repository.UserRepository;
import com.JWTToken.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	private UserRepository useRrepo;
	

	@Override
	public User saveUser(User user) {
		return useRrepo.save(user);
	}

	@Override
	public User getemail(User user) {
		String email=user.getEmail();
		return useRrepo.findByEmail(email);
		}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User datbaseuser = useRrepo.findByEmail(username);
		org.springframework.security.core.userdetails.User userinternal=null;
		
		if(datbaseuser == null) {
			throw new UsernameNotFoundException("User not found");
		}else {
			String role= datbaseuser.getRole();
			Set<GrantedAuthority> authority = new HashSet<>();
			authority.add(new SimpleGrantedAuthority(role));
			userinternal=new org.springframework.security.core.userdetails.User(username,datbaseuser.getPassword(),authority);
		}
		
		return userinternal;
	}
	
	@Bean
	public PasswordEncoder encodePass(){
		return NoOpPasswordEncoder.getInstance();
	}
}
