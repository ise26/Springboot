package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Productdto;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository prepo;
	
	@Override
	public void save(Productdto productdto, Category category) {
		Product product =  new Product();
		product.setName(productdto.getName());
		product.setColor(productdto.getColor());
		product.setPrice(productdto.getPrice());
		product.setSize(productdto.getSize());
		product.setDescription(productdto.getDescription());
		product.setImage(productdto.getImage());
		product.setCategory(category);
		
		prepo.save(product);
	}

	@Override
	public List<Product> getProduct() {
		return (List<Product>) prepo.findAll();
	}

	@Override
	public Optional<Product> getProductById(Long id) {
		return prepo.findById(id);
	}

	@Override
	public void deleteProduct(Long id) {
		prepo.deleteById(id);
		
	}

	@Override
	public void update(Productdto productdto, Long id) {
		System.out.println("hello");
		Optional<Product> existingdata = prepo.findById(id);
			
		if(existingdata.isPresent()) {
		Product dumyproduct = existingdata.get();
		System.out.println(dumyproduct.getName()+"12345");
		 
		 Product product1 =  new Product();
			product1.setPid(id);
			product1.setName(productdto.getName()!=null ? productdto.getName():dumyproduct.getName());
			System.out.println(product1.getName());
			System.out.println(productdto.getName());
			System.out.println(dumyproduct.getName());
			
			product1.setColor(productdto.getColor()!=null ? productdto.getColor():dumyproduct.getColor());
			System.out.println(product1.getColor());
			
			product1.setPrice(productdto.getPrice()!=null ? productdto.getPrice():dumyproduct.getPrice());
			System.out.println(product1.getPrice());
			
			product1.setSize(productdto.getSize()!=null ? productdto.getSize():dumyproduct.getSize());
			
			
			product1.setDescription(productdto.getDescription()!=null ? productdto.getDescription():dumyproduct.getDescription());
			
			
			product1.setImage(productdto.getImage()!=null ? productdto.getImage():dumyproduct.getImage());
			
			
			product1.setCategory(dumyproduct.getCategory());
			prepo.save(product1);
		}
		
	}
	
}
