package com.entity.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "orders_onetomany")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderTrackingNumber;

	private int totalQuantity;

	private Double totalPrice;

	private String status;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "oid")
	private Set<OrderItems> orderitem = new HashSet<>();

}
