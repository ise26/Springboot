package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	Optional<Product> findByName(String name);

}
