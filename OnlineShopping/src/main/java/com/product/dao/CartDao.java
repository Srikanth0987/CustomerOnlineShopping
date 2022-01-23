package com.product.dao;

import java.util.List;

import com.product.model.Cart;

public interface CartDao {
	public List<Cart> fetchAllCart();
	public void addCart(Cart input);
	public Cart findCartById(int id);
	public boolean updateCartCount(int cartId);
	public void deleteCart(int id);
}
