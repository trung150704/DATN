package com.mt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mt.entity.Product;

public interface ProductService {
    List<Product> findAll(); // Phương thức này không phân trang
    
    List<Product> findByCategoryId(String cid);
    
    Page<Product> findByPriceRange(double minPrice, double maxPrice, Pageable pageable);
    
    Product findById(String id); // Sửa lại thành kiểu trả về Product

    Page<Product> findAll(Pageable pageable); // Phương thức phân trang
  
    Page<Product> findByCategoryId(String cid, Pageable pageable); // Phương thức phân trang theo danh mục

    Product create(Product product);

    Product update(Product product);

    void delete(String id); // Chỉnh sửa kiểu tham số thành String
}
