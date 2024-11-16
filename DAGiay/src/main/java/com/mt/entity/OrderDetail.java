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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private  Integer quantity;
    private Double price;
    //private String productName;
    private String size;
    @ManyToOne
    @JoinColumn(name = "orderid")
    Order order;

    @ManyToOne
    @JoinColumn(name = "productid")
    Product product;

    
}
