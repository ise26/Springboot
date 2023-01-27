package com.example.demo.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Orderdto {

	private Long id;
	private int quantity;
	private String paymentMode;
	private String shippingAddress;
	private Date orderDate;
	private Long customer_id;
	private Long product_pid;
	private Date startDate;
	private Date endDate;
	
}
