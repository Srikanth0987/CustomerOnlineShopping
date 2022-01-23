package com.product.services;

import java.util.List;

import com.product.model.User;

public interface UserService {
	public List<User> fetchAllUsers();
	public void addUser(User input);
	public boolean findByName(String name);
	public User findUserById(long id);
	public void deleteUser(long id);
}
