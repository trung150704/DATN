package com.mt.serviceImplement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mt.entity.Order;
import com.mt.entity.OrderDetail;
import com.mt.repository.OrderDetailRepository;
import com.mt.repository.OrderRepository;
import com.mt.service.OrderDetailService;

@Service
public class OrderDetailServiceImplement implements OrderDetailService {

    @Autowired
    OrderDetailRepository detailRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderDetail> findAll() {
        return detailRepository.findAll();
    }

    @Override
    public OrderDetail findById(Integer id) {
        return detailRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Integer orderId, List<OrderDetail> orderDetails) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            for (OrderDetail detail : orderDetails) {
                detail.setOrder(order);
                detailRepository.save(detail);
            }
        }
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) { // Đổi tên thành save
        return detailRepository.save(orderDetail);
    }
}
