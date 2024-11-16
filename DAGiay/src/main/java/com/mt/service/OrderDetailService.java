package com.mt.service;

import java.util.List;
import com.mt.entity.OrderDetail;

public interface OrderDetailService {
    List<OrderDetail> findAll();
    OrderDetail findById(Integer id);
    void save(Integer orderId, List<OrderDetail> orderDetails);
    OrderDetail save(OrderDetail orderDetail); 
}
