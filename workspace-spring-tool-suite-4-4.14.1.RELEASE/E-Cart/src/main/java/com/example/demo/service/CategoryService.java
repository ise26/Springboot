package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;

@Repository
public interface CategoryService {
	public List<Category> getCategories();
	
	public Optional<Category> getCategoryById(Long id);
	
	public Category addCategory(Category category);
	
	public void deleteCategory(Long id);
	
	public Category updateCategory(Long id, Category category);
}
