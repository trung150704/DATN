package com.mt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.mt.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

	@Query("SELECT o FROM Order o WHERE o.account.username = :username")
	List<Order> findByUsername(String username);

}
