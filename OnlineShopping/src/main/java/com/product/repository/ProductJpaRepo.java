package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.Product;

public interface ProductJpaRepo extends JpaRepository<Product, Integer>{
	
	public Product findByName(String name);
}
