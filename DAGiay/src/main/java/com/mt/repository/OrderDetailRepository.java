package com.mt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mt.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{

}
