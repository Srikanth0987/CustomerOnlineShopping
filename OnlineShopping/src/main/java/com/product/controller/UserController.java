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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.product.model.User;
import com.product.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserController", description = "REST Apis related to user Entity!!!!")
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@EnableWebMvc
public class UserController {
   private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;

	@ApiOperation(value = "Get list of user in the System  ", response = User.class, tags = "user")
	@RequestMapping(value = "/", method = RequestMethod.GET)
    @ExceptionHandler(Throwable.class)
	public List<User> fetchAllUsers(){
		logger.info("Inside fetchAllUsers Controller");
		return userService.fetchAllUsers();
	}	
	
	@ApiOperation(value = "Adding User in the System   ", response = ResponseEntity.class, tags = "user")
	@RequestMapping(value = "/", method = RequestMethod.POST)
    @ExceptionHandler(Throwable.class)
	public ResponseEntity<Void> addUser(@RequestBody User user){
		logger.info("Inside addUser Controller");
		if(userService.findByName(user.username)){
			logger.info("username already found");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		userService.addUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get User based on ID in the System   ", response = ResponseEntity.class, tags = "user")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ExceptionHandler(Throwable.class)
	public ResponseEntity<User> findUserById(@PathVariable("id")long id){
		logger.info("Inside findUserById Controller");
		ResponseEntity<User> response = null; 
		User user = userService.findUserById(id);
		if(user==null){
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			logger.info("No User found based on the user id");
		}
		else{
			response = new ResponseEntity<>(user, HttpStatus.OK);
			logger.info("User found based on the user id");
		}
		return response;
	}
	
	@ApiOperation(value = "Delete User based on ID in the System  ", response = ResponseEntity.class, tags = "user")
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Throwable.class)
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
		logger.info("Inside FetchAllcart Controller");
		ResponseEntity<String> response = null;
		try{
			userService.deleteUser(id);
			response = ResponseEntity.ok().body("User has been deleted successfully.");
			logger.info("User has been deleted successfully");
		}
		catch(EmptyResultDataAccessException e){
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			logger.info("No User found based on the user id to delete");
		}
		return response;
	}

}
