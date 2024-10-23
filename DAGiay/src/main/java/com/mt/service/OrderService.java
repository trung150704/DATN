package com.mt.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.mt.entity.Order;

public interface OrderService {
	Order create(JsonNode orderData);

	Order findById(String id);

	List<Order> findByUsername(String username);
}
