package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Productdto;

public interface ProductService {

	public void save(Productdto product, Category category);
	
	public List<Product> getProduct();
	
	public Optional<Product> getProductById(Long id);
	
	public void deleteProduct(Long id);
	
	public void update(Productdto productdto, Long id);
}
