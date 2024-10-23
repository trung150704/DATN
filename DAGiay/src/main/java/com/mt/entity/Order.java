package com.mt.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
  
    private String id;
    @Temporal(TemporalType.DATE)
    @Column(name = "Created_at")
    private Date created_at = new Date();
    
    private Double amout;
    private String status;
    private String address;

    @ManyToOne
    @JoinColumn(name = "accountid")
    private Account account;
    
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
    
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<Payment> payments;

    // Getters and Setters
}
