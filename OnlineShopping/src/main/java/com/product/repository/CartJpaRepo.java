package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.product.model.Cart;

public interface CartJpaRepo extends JpaRepository<Cart, Integer>{
	@Transactional
	@Modifying
	@Query(value = "update cart set count = (select count from cart where id = :id ) +1 where id = :id", nativeQuery = true)
	public boolean updateCartCount(@Param("id") int id);
	
	
}
