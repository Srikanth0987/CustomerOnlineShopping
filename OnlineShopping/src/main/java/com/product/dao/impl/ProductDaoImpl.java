package com.product.dao.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.product.dao.ProductDao;
import com.product.model.Product;
import com.product.repository.ProductJpaRepo;

@Component
public class ProductDaoImpl implements ProductDao{
	private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
	@Autowired
	ProductJpaRepo productRepository;
		
	public List<Product> fetchAllProducts(String orderBy,String direction, int pageNumber,int pageSize) {
		logger.info("Inside fetchAllProducts Dao");
		Pageable pageable;
		if(orderBy.equalsIgnoreCase("price")) {
			pageable = direction.equalsIgnoreCase("desc")? PageRequest.of(pageNumber,pageSize,Sort.by("price").descending()) : PageRequest.of(pageNumber,pageSize,Sort.by("price")) ;
		}else if(orderBy.equalsIgnoreCase("name")) {
			pageable = direction.equalsIgnoreCase("desc")? PageRequest.of(pageNumber,pageSize,Sort.by("name").descending()) : PageRequest.of(pageNumber,pageSize,Sort.by("name")) ;
		}else {
			pageable = direction.equalsIgnoreCase("desc")? PageRequest.of(pageNumber,pageSize,Sort.by("id").descending()) : PageRequest.of(pageNumber,pageSize,Sort.by("id")) ;
		}
		logger.info("Pagable is applied");
		try {
			Page<Product> pageProduct = productRepository.findAll(pageable);
			return pageProduct.getContent();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
		
	}

	public void addProduct(Product input) {
		logger.info("Inside addProduct Dao Started");
		try {
			productRepository.save(input);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		logger.info("Inside addProduct Dao Ended");
	}
	
	public boolean findByName(String name) {
		logger.info("Inside findByName Dao");
		try {
			Product product = productRepository.findByName(name);
			if(product == null){
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}		
		return true;
	}
	
	public Product findProductById(int id) {
		logger.info("Inside findProductById Dao");
		try {
			Optional<Product> product = productRepository.findById(id);
			if(product.isPresent())
			{
				return product.get();
			}	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
		return null;
	}

	public void deleteProduct(int id) {
		logger.info("Inside deleteProduct Dao Started");
		try {
			productRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		logger.info("Inside deleteProduct Dao Ended");
	}
	
}
