package com.mt.entity;

import javax.persistence.*;


import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "OrderDetails")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    private String id;

    private int count;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "Orderid")
    Order order;

    @ManyToOne
    @JoinColumn(name = "Productid")
    Product product;

    
}
