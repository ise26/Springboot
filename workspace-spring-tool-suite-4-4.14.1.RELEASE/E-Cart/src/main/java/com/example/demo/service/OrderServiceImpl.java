package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Orderdto;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.entity.Productdto;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Override
	public List<Orders> getorders() {
		
		return (List<Orders>) orderRepo.findAll();
	}

	@Override
	public void save(Orderdto orderdto, Customer customer, Product product) {
		Orders order =  new Orders();
		order.setOrderDate(orderdto.getOrderDate());
		order.setQuantity(orderdto.getQuantity());
		order.setPaymentMode(orderdto.getPaymentMode());
		order.setShippingAddress(orderdto.getShippingAddress());
		order.setCustomer(customer);
		order.setProduct(product);
		
		orderRepo.save(order);
		
	}

	@Override
	public List<Orders> getOrderByDate(java.sql.Date orderDate) {
		return orderRepo.findByorderDate(orderDate);
	}


	
	public List<Orders> getOrderByProductName(String name) {

		return orderRepo.findByProductName(name);
	}

	@Override
	public List<Orders> getOrderByCategory(String name) {
		
		return orderRepo.findByCategory(name);
	}

	@Override
	public List<Orders> getOrderByRangeDate(java.sql.Date startDate, java.sql.Date endDate) {
		
		return orderRepo.findByorderDateBetween(startDate, endDate);
	}

}
