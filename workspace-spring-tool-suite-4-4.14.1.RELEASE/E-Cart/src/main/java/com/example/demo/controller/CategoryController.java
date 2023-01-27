package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService catService;
	
	@PostMapping("/add")
	public Category addcategory(@RequestBody Category category) {
		return catService.addCategory(category);
	}
	
	@GetMapping("/getCategory")
	public List<Category> getCategories() {
		return catService.getCategories();
	}
	
	@GetMapping("/getCategory/{id}")
	public Optional<Category> getCategoryById(@PathVariable Long id) {
	
		return catService.getCategoryById(id);
	}
	
	@PutMapping("/update/{id}")
	public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
		
		return catService.updateCategory(id,category);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteCategory(@PathVariable Long id) {
		catService.deleteCategory(id);

	}
}
