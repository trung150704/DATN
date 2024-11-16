package com.mt.DTO;

import com.mt.entity.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private Long id;
    private String size;
    private Integer quantity;
    private Double price;
    private String productName;
    private Product product; // Liên kết đến Product entity

    // Getters và setters
}
