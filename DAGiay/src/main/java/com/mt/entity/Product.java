package com.mt.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "Products")
public class Product implements Serializable {
	
    @Id
    private Integer id;

    private String name;
    private String describe;
    private Double price;
    private String images;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date create_at = new Date();

    @ManyToOne
    @JoinColumn(name = "categoryid")
    @JsonIgnore
    private Category category;

//    @OneToMany(mappedBy = "product")
//    private List<ProductPromotion> productPromotions;
//
//    @OneToMany(mappedBy = "product")
//    private List<FavoriteProduct> favoriteProducts;
//
//    @OneToMany(mappedBy = "product")
//    private List<OrderDetail> orderDetails;
//
//    @OneToMany(mappedBy = "product")
//    private List<SizeProduct> sizeProducs;

    // Getters and Setters
}
