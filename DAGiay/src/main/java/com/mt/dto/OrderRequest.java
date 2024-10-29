package com.mt.dto;

import com.mt.entity.Order;
import com.mt.entity.OrderDetail;
import java.util.List;

public class OrderRequest {
    private Order order;
    private List<OrderDetail> orderDetails;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
