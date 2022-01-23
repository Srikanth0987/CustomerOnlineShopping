package com.product.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dao.ProductDao;
import com.product.model.Product;
import com.product.services.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	ProductDao productDao;
		
	public List<Product> fetchAllProducts(String orderBy,String direction, int pageNumber,int pageSize) {
		logger.info("Inside fetchAllProducts Service");
		return productDao.fetchAllProducts(orderBy, direction, pageNumber, pageSize);
	}

	public void addProduct(Product input) {
		logger.info("Inside addProduct Service Started");
		productDao.addProduct(input);
		logger.info("Inside addProduct Service Ended");
	}
	
	public boolean findByName(String name) {
		logger.info("Inside findByName Service");
		return productDao.findByName(name);
	}
	
	public Product findProductById(int id) {
		logger.info("Inside findProductById Service");
		return productDao.findProductById(id);
	}

	public void deleteProduct(int id) {
		logger.info("Inside deleteProduct Service Started");
		productDao.deleteProduct(id);
		logger.info("Inside deleteProduct Service Ended");

	}
	
}
