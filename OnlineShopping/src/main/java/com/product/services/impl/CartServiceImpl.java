package com.product.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dao.CartDao;
import com.product.model.Cart;
import com.product.services.CartService;

@Service
public class CartServiceImpl implements CartService {
	private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
	@Autowired
	CartDao cartdao;
		
	public List<Cart> fetchAllCart() {
		logger.info("Inside fetchAllCart Service");
		return cartdao.fetchAllCart();
	}

	public void addCart(Cart input) {
		logger.info("Inside addCart Service Started");
		cartdao.addCart(input);
		logger.info("Inside addCart Service Ended");
	}
	
	public Cart findCartById(int id) {
		logger.info("Inside findCartById Service");
		return cartdao.findCartById(id);
	}

	public boolean updateCartCount(int cartId) {
		logger.info("Inside updateCartCount Service");
		return cartdao.updateCartCount(cartId);
	}
	public void deleteCart(int id) {
		logger.info("Inside deleteCart Service Started");
		cartdao.deleteCart(id);
		logger.info("Inside deleteCart Service Ended");

	}
	
}
