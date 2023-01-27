package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.query.NativeQuery;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Orders;

public interface OrderRepository extends CrudRepository<Orders, Long> {
	@org.springframework.data.jpa.repository.Query(value = "select * from orders where order_date=?1",nativeQuery = true)
	public List<Orders> findByorderDate(java.sql.Date orderDate);
	
	@org.springframework.data.jpa.repository.Query(value = "select * from orders o join product p on o.id=p.pid where name=?1", nativeQuery = true)
	public List<Orders> findByProductName(String name);
	
	@org.springframework.data.jpa.repository.Query(value = "select * from orders o join product p on o.id=p.pid join category c on p.category_id=c.id where c.name=?1",nativeQuery = true)
	public List<Orders> findByCategory(String name);
	
	public List<Orders> findByorderDateBetween(java.sql.Date startDate, java.sql.Date enddate);
}
