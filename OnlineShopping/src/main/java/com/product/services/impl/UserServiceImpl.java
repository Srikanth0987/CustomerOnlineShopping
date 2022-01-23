package com.product.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dao.UserDao;
import com.product.model.User;
import com.product.services.UserService;


@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	UserDao userdao;
		
	public List<User> fetchAllUsers() {
		logger.info("Inside fetchAllUsers Service");
		return userdao.fetchAllUsers();
	}

	public void addUser(User input) {
		logger.info("Inside addUser Service Started");
		userdao.addUser(input);
		logger.info("Inside addUser Service Ended");
	}
	
	public boolean findByName(String name) {
		logger.info("Inside findByName Service");
		return userdao.findByName(name);
	}
	
	public User findUserById(long id) {
		logger.info("Inside findUserById Service");
		return userdao.findUserById(id);
	}

	public void deleteUser(long id) {
		logger.info("Inside deleteUser Service Started");
		userdao.deleteUser(id);
		logger.info("Inside deleteUser Service Ended");
	}
	
}
