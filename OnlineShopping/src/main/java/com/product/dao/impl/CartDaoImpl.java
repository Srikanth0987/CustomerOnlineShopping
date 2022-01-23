package com.product.dao.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.dao.CartDao;
import com.product.model.Cart;
import com.product.repository.CartJpaRepo;

@Component
public class CartDaoImpl implements CartDao{
	private static final Logger logger = LoggerFactory.getLogger(CartDaoImpl.class);
	@Autowired
	CartJpaRepo cartRepository;
		
	public List<Cart> fetchAllCart() {
		logger.info("Inside fetchAllCart Dao");
		try {
			return cartRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}

	public void addCart(Cart input) {
		logger.info("Inside addCart Dao Started");
		try {
			cartRepository.save(input);
		} catch (Exception e) {
			
		}
		logger.info("Inside addCart Dao Ended");
	}
	
	public Cart findCartById(int id) {
		logger.info("Inside findCartById Dao");
		try {
			Optional<Cart> cart = cartRepository.findById(id);
			if(cart.isPresent())
			{
				return cart.get();
			}
		} catch (Exception e) {
			
		}		
		return null;
	}

	public boolean updateCartCount(int cartId) {
		logger.info("Inside updateCartCount Dao");
		try {
			return cartRepository.updateCartCount(cartId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
		
	}
	public void deleteCart(int id) {
		logger.info("Inside deleteCart Dao Started");
		try {
			cartRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		logger.info("Inside deleteCart Dao Ended");
	}
}
