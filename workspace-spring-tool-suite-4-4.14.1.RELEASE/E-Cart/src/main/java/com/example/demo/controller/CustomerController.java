package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService custservice;
	
	@GetMapping("/viewcustomers")
	public List<Customer> getCustomer(){
		return custservice.getCustomers();
	}
	
	@PostMapping("/savecustomer")
	public Customer addCustoamer(@RequestBody Customer customer) {
		return custservice.addCustomer(customer);
	}
}
