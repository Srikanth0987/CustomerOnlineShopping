package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product.model.User;


public interface UserJpaRepo extends JpaRepository<User, Long>{
	
	@Query(value = "SELECT * FROM User u where username = :username limit 1", nativeQuery = true)
	public User findByUsername(@Param("username") String username);
}
