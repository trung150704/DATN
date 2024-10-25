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
import com.mt.repository.OrderDetailRepository;
import com.mt.repository.OrderRepository;
import com.mt.service.OrderService;

@Service
public class OrderServiceImplement implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository  orderDetailRepository;

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        
        Order hoaDon = mapper.convertValue(orderData, Order.class);
        orderRepository.save(hoaDon);
        
        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
        };
        
        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
                .stream().peek(d -> d.setOrder(hoaDon)).collect(Collectors.toList());
        orderDetailRepository.saveAll(details);
        return hoaDon;
    }

//    @Override
//    public Order findById(String id) {  // Changed from Integer to Long
//        return orderRepository.findById(id).get(); // Changed from get() to orElse(null) for safety
//    }

    @Override
    public List<Order> findByUsername(String id) {
        return orderRepository.findByUsername(id);
    }

//	@Override
//	public List<Order> findAll() {
//		return orderRepository.findAll();
//	}
}
