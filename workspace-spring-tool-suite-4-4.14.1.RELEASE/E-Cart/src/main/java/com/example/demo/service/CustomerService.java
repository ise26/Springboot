package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;

@Service
public interface CustomerService {
	public List<Customer> getCustomers();
	
	public Customer addCustomer(Customer customer); 
}
