package com.product.dao.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.dao.UserDao;
import com.product.model.User;
import com.product.repository.UserJpaRepo;

@Component
public class UserDaoImpl implements UserDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	UserJpaRepo userRepository;
		
	public List<User> fetchAllUsers() {
		logger.info("Inside fetchAllProducts Dao");	
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}

	public void addUser(User input) {
		logger.info("Inside addUser Dao Started");
		try {
			userRepository.save(input);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		logger.info("Inside addUser Dao Ended");
	}
	
	public boolean findByName(String name) {
		logger.info("Inside findByName Dao");
		try {
			User user = userRepository.findByUsername(name);
			if(user == null){
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
		return true;
	}
	
	public User findUserById(long id) {
		logger.info("Inside findUserById Dao");
		try {
			Optional<User> user = userRepository.findById(id);
			if(user.isPresent())
			{
				return user.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return null;

	}

	public void deleteUser(long id) {
		logger.info("Inside deleteUser Dao Started");
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		logger.info("Inside deleteUser Dao Rnded");
	}
	
}
