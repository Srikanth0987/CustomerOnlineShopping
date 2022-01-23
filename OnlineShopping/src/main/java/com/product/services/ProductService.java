package com.product.services;

import java.util.List;

import com.product.model.Product;

public interface ProductService {
	public List<Product> fetchAllProducts(String orderBy,String direction, int pageNumber,int pageSize);
	public void addProduct(Product input);	
	public boolean findByName(String name);
	public Product findProductById(int id);
	public void deleteProduct(int id);
}
