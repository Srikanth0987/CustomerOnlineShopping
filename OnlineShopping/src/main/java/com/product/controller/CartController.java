package com.product.controller;

import java.util.List;

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

import com.product.model.Cart;
import com.product.services.CartService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Api(value = "CartController", description = "REST Apis related to cart Entity!!!!")
@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
@EnableWebMvc
public class CartController {
   private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	CartService cartService;
	
    @ExceptionHandler(Throwable.class)
	@ApiOperation(value = "Get list of cart in the System  ", response = Cart.class, tags = "cart")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Cart> fetchAllCart(){
		logger.info("Inside FetchAllcart Controller");
		return cartService.fetchAllCart();
	}	
	
    @ExceptionHandler(Throwable.class)
	@ApiOperation(value = "Adding cart in the System   ", response = ResponseEntity.class, tags = "cart")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Void> addCart(@RequestBody Cart cart){
		logger.info("Inside addCart Controller");
		cartService.addCart(cart);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
    @ExceptionHandler(Throwable.class)
	@ApiOperation(value = "updating cart count based on cart id in the system   ", response = ResponseEntity.class, tags = "cart")
	@RequestMapping(value = "/cart-count", method = RequestMethod.PUT)
	public ResponseEntity<String> updateCart(@RequestParam(value = "cartid", required = true) int cartid){
		logger.info("Inside updateCart Controller");
		ResponseEntity<String> response = null;
		if(cartService.updateCartCount(cartid) ) {
			response = ResponseEntity.ok().body("Cart Count has been Updated successfully.");
			logger.info("Cart Count has been Updated successfully");
		}else {
			response = new ResponseEntity<>("No Cart Product Found", HttpStatus.NOT_FOUND);
			logger.info("No Cart Product Found");
		}
		return response;
	}

    @ExceptionHandler(Throwable.class)
	@ApiOperation(value = "Get Cart based on ID in the System   ", response = ResponseEntity.class, tags = "cart")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cart> findCartById(@PathVariable("id")int id){
		logger.info("Inside findCartById Controller");
		ResponseEntity<Cart> response = null; 
		Cart cart = cartService.findCartById(id);
		if(cart==null){
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			logger.info("No Data found for the cart id");
		}
		else{
			response = new ResponseEntity<>(cart, HttpStatus.OK);
			logger.info("Data found for the cart id");
		}
		return response;
	}
	
	@ApiOperation(value = "Delete Cart based on ID in the System  ", response = ResponseEntity.class, tags = "cart")
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Throwable.class)
	public ResponseEntity<String> deleteCart(@PathVariable("id") int id){
		logger.info("Inside deleteCart Controller");
		ResponseEntity<String> response = null;
		try{
			cartService.deleteCart(id);
			response = ResponseEntity.ok().body("Cart has been deleted successfully.");
			logger.info("Cart has been deleted successfully");
		}
		catch(EmptyResultDataAccessException e){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			logger.info("No data found to delete for the cart id");
		}
		return response;
	}

}
