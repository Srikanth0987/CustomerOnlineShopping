package com.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.product.model.Product;
import com.product.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ProductController", description = "REST Apis related to product Entity!!!!")
@RestController
@CrossOrigin("*")
@RequestMapping("/product")
@EnableWebMvc
public class ProductController {
   private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	@ApiOperation(value = "Test APi ", response = ResponseEntity.class, tags = "test")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    @ExceptionHandler(Throwable.class)
	public ResponseEntity<String> test(){
		return new ResponseEntity<>("Demo API", HttpStatus.OK);
	}

	@ApiOperation(value = "Get list of Products in the System  ", response = Product.class, tags = "product")
	@RequestMapping(value = "/", method = RequestMethod.GET)
    @ExceptionHandler(Throwable.class)
	public List<Product> fetchAllProducts(@RequestParam(value = "orderBy", required = false ,defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", required = false ,defaultValue = "asc") String direction, 
			@RequestParam(value = "pageNumber", required = false ,defaultValue = "0") int pageNumber, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize){
		logger.info("Inside fetchAllProducts Controller");
		return productService.fetchAllProducts(orderBy, direction, pageNumber, pageSize);
	}	
	
	@ApiOperation(value = "Adding Product in the System   ", response = ResponseEntity.class, tags = "product")
	@RequestMapping(value = "/", method = RequestMethod.POST)
    @ExceptionHandler(Throwable.class)
	public ResponseEntity<Void> addProduct(@RequestBody Product product){
		logger.info("Inside addProduct Controller");
		if(productService.findByName(product.name)){
			logger.info("product already found");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		 productService.addProduct(product);
		 return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get Product based on ID in the System   ", response = ResponseEntity.class, tags = "product")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ExceptionHandler(Throwable.class)
	public ResponseEntity<Product> fetchProduct(@PathVariable("id")int id){
		logger.info("Inside fetchProduct Controller");
		ResponseEntity<Product> response = null; 
		Product product = productService.findProductById(id);
		if(product==null){
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			logger.info("No Products found based on produt id");
		}
		else{
			response = new ResponseEntity<>(product, HttpStatus.OK);
			logger.info("Products found based on produt id");
		}
		return response;
	}
	
	@ApiOperation(value = "Delete Product based on ID in the System  ", response = ResponseEntity.class, tags = "product")
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Throwable.class)
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
		logger.info("Inside deleteProduct Controller");
		ResponseEntity<String> response = null;
		try{
			productService.deleteProduct(id);
			response = ResponseEntity.ok().body("Product has been deleted successfully.");
			logger.info("Product has been deleted successfully");
		}
		catch(EmptyResultDataAccessException e){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			logger.info("No Products found based on produt id to delete");
		}
		return response;
	}

}
