package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Productdto;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService pservice;
	
	@Autowired
	private CategoryRepo crepo;
	
	@PostMapping("/addproduct")
	public void addProduct(@RequestBody Productdto product) {
		Optional<Category> category = crepo.findById(product.getCategory_id());
		
		pservice.save(product,category.get());
	}
	
	@GetMapping("/getproducts")
	public List<Product> getProduct() {
		return pservice.getProduct();
	}
	
	@GetMapping("/getproduct/{id}")
	public Optional<Product> getProductByid(@PathVariable Long id) {
	
		return pservice.getProductById(id);
	}
	
	@DeleteMapping("/deleteproduct/{id}")
	public void deleteProduct(@PathVariable Long id) {
		pservice.deleteProduct(id);
		
	}
	
	@PutMapping("/updateproduct/{id}")
	public void update(@PathVariable Long id, @RequestBody Productdto product) {
		 pservice.update(product, id);
		
	}
}
