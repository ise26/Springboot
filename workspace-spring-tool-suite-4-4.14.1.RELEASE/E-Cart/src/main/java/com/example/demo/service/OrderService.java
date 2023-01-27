package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Orderdto;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;

public interface OrderService {

	public void save(Orderdto orderdto, Customer customer, Product product);
	
	public List<Orders> getorders();
	
	public List<Orders> getOrderByProductName(String name);
	
	public List<Orders> getOrderByCategory(String name);

	List<Orders> getOrderByDate(java.sql.Date orderDate);

	List<Orders> getOrderByRangeDate(java.sql.Date startDate, java.sql.Date endDate);
}
