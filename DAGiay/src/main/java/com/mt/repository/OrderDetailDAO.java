package com.mt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mt.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, String>{

}
