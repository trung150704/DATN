package com.mt.entity;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@Entity
@Table(name = "Payments")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
   
    private String id;
    @Temporal(TemporalType.DATE)
    @Column(name = "Created_at")
    private Date created_at = new Date();
    private String count;
    private String payment;

    @ManyToOne
    @JoinColumn(name = "Orderid")
    private Order order;

    // Getters and Setters
}
