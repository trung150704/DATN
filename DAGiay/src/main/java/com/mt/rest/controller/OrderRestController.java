package com.mt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mt.entity.Order;
import com.mt.entity.OrderDetail;
import com.mt.service.OrderDetailService;
import com.mt.service.OrderService;
import com.mt.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	ProductService productService;
	@GetMapping("")
	public List<Order> findAll() {
		return orderService.findAll();
	}

	@GetMapping("/{id}")
	public Order findByID(@PathVariable("id") Integer id) {
		return orderService.findById(id);
	}
	
	@PostMapping("")
	public Order save(@RequestBody Order order) {
		return orderService.save(order);
	}

	@PostMapping("/{orderId}/details")
	public ResponseEntity<?> saveOrderDetails(@PathVariable Integer orderId, @RequestBody List<OrderDetail> orderDetails) {
	    orderDetailService.save(orderId, orderDetails); // Save order details
	    return ResponseEntity.ok("Order details saved successfully");
	}

}
