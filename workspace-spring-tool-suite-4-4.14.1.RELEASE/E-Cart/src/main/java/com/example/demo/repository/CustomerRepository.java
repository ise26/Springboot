package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Query(value="select * from customer where email=?1",nativeQuery = true)
	public Customer findByEmail(String email);
}
