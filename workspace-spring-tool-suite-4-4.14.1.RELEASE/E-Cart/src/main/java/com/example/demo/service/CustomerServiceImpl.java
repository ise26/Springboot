package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

	@Autowired
	private CustomerRepository custrepo;

	@Override
	public List<Customer> getCustomers() {

		return (List<Customer>) custrepo.findAll();
	}

	@Override
	public Customer addCustomer(Customer customer) {
		Customer cust = new Customer();
		cust.setDateOfBirth(customer.getDateOfBirth());
		cust.setEmail(customer.getEmail());
		cust.setFullName(customer.getFullName());
		cust.setMobile(customer.getMobile());
		cust.setPassword(customer.getPassword());
		cust.setRole("customer");
		return custrepo.save(cust);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = custrepo.findByEmail(email);
		System.out.println(customer.getEmail());
		User user = null;
		if (customer.getEmail() == null) {
			throw new RuntimeException("Something went wrong");
		} else {
			String role=customer.getRole();
			Set<GrantedAuthority> authority=new HashSet<>();
			authority.add(new SimpleGrantedAuthority(role));
			user = new User(email,customer.getPassword(),authority);
			return user;
		}

		
	}

}
