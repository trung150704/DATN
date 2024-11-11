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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "Created_at")
    private Date createdAt = new Date();
    
    @Column(name = "hoten")
    private String hoten;
    
    @Column(name = "sdt")
    private String sdt;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "status")
    private Boolean status;
    
    @ManyToOne
    @JoinColumn(name = "accountid",insertable = false, updatable = false)
    private Account account;
    
    @ManyToOne
    @JoinColumn(name = "paymentid")
    private Payment payment;
    
    @Column(name = "totalamount")
    private Double totalamount;
    
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
    
}
