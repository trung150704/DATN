package com.mt.serviceImplement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mt.entity.Order;
import com.mt.entity.OrderDetail;
import com.mt.repository.OrderDetailDAO;

import com.mt.repository.OrderRepository;
import com.mt.service.OrderService;

@Service
public class OrderServiceImplement implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailDAO orderDetailRepository;

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.convertValue(orderData, Order.class);
        orderRepository.save(order);
        
        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
                .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        orderDetailRepository.saveAll(details);
        return order;
    }

    @Override
    public void createOrder(Order order, List<OrderDetail> orderDetails) {
        if (order == null || orderDetails == null || orderDetails.isEmpty()) {
            throw new IllegalArgumentException("Order and order details must not be null or empty");
        }

        // Debugging logs
        System.out.println("Saving Order: " + order);
        System.out.println("Saving Order Details: " + orderDetails);

        orderRepository.save(order); // Lưu đơn hàng

        // Cập nhật quan hệ giữa Order và OrderDetail
        for (OrderDetail detail : orderDetails) {
            detail.setOrder(order); // Gán Order cho OrderDetail
            orderDetailRepository.save(detail); // Lưu OrderDetail
        }
    }

    @Override
    public List<Order> findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order save(Order order) {
	    return orderRepository.save(order);
	}

}
