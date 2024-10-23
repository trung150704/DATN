package com.mt.entity;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "Products")
public class Product implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private String describe;
    private Double price;
    private String images;
    @Temporal(TemporalType.DATE)
    @Column(name = "Created_at")
    private Date create_at = new Date();

    @ManyToOne
    @JoinColumn(name = "Categoryid")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductPromotion> productPromotions;

    @OneToMany(mappedBy = "product")
    private List<FavoriteProduct> favoriteProducts;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    private List<SizeProduct> sizeProducs;

    // Getters and Setters
}
