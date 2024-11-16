package com.mt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mt.entity.Order;
import com.mt.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	@Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderDetails WHERE o.id = :orderId")
	Order findByIdWithItems(@Param("orderId") Integer orderId);

	List<OrderDetail> findByOrderId(Integer orderId);
}
