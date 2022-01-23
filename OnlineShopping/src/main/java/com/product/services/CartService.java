package com.product.services;

import java.util.List;

import com.product.model.Cart;

public interface CartService {
	public List<Cart> fetchAllCart();
	public void addCart(Cart input);
	public Cart findCartById(int id);
	public boolean updateCartCount(int cartId);
	public void deleteCart(int id);
}
