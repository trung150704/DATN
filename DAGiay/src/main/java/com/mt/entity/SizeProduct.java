package com.mt.entity;

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@IdClass(SizeProductKey.class)  // Sử dụng composite key
@Table(name = "SizeProduct")
public class SizeProduct implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Productid")
    private String Productid; // Khai báo thuộc tính cơ bản

    @Id
    @Column(name = "Sizeid")
    private String Sizeid; // Khai báo thuộc tính cơ bản

    private int count;

    // Quan hệ với Product và Size
    @ManyToOne
    @JoinColumn(name = "Productid", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "Sizeid", insertable = false, updatable = false)
    private Size size;

    // Getters và Setters
}
