package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Orderdto;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.entity.Productdto;
import com.example.demo.exception.NoOrderfoundException;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerRepository custrepo;
	@Autowired
	private ProductRepository productrepo;
	
	@PostMapping("/addorder")
	public void addorder(@RequestBody Orderdto orderdto) {
		Optional<Customer> customer = custrepo.findById(orderdto.getCustomer_id());
		Optional<Product> product = productrepo.findById(orderdto.getProduct_pid());
		
		orderService.save(orderdto, customer.get(),product.get());
	}

	@GetMapping("/getallorders")
	public List<Orders> getorders() {

		return orderService.getorders();
	}
	
	@GetMapping("/orderByDate")
	public List<Orders> getOrderByDate(@RequestBody Orders order) {
		return orderService.getOrderByDate(order.getOrderDate());
	}
	
	@GetMapping("/orderbyproduct/{name}")
	public List<Orders> getOrderByProductName(@PathVariable String name){
		
		return orderService.getOrderByProductName(name);
	}
	
	@GetMapping("/orderbyproductname")
	public List<Orders> getOrderByProductName(@RequestBody Orders order){
		return orderService.getOrderByProductName(order.getProduct().getName());
		
	}
	
	@GetMapping("/orderbycategory")
	public List<Orders> getOrderByCategory(@RequestBody Orders order){
		return orderService.getOrderByCategory(order.getProduct().getCategory().getName());
	}
	
	@GetMapping("/orderbyrange")
	public List<Orders> getOrderByRangeDate(@RequestBody Orderdto order) {
		
		return orderService.getOrderByRangeDate(order.getStartDate(),order.getEndDate());
	}

}
