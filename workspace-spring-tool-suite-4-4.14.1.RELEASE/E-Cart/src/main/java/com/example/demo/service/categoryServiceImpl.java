package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepo;

@Service
public class categoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo catRepo;
	
	@Override
	public List<Category> getCategories() {
		return (List<Category>) catRepo.findAll();
	}

	@Override
	public Optional<Category> getCategoryById(Long id) {
	
		return catRepo.findById(id);
	
	}

	@Override
	public Category addCategory(Category category) {
		return catRepo.save(category);
		
	}

	@Override
	public void deleteCategory(Long id) {
		catRepo.deleteById(id);

	}

	@Override
	public Category updateCategory(Long id, Category category) {
		Optional<Category> existing = catRepo.findById(id);
		if(existing.isPresent()) {
			Category previousdata=existing.get();
			
			previousdata.setName(category.getName()!=null ? category.getName():previousdata.getName());
			previousdata.setImgUrl(category.getImgUrl()!=null ? category.getImgUrl() : previousdata.getImgUrl());
			catRepo.save(previousdata);
		}
		return category;
		
	}

}
