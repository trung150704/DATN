package com.mt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mt.entity.Product;

public interface ProductService {

    List<Product> findAll(); // Phương thức này không phân trang

    Product findById(Integer id);

    Page<Product> findAll(Pageable pageable); // Phương thức phân trang

    Page<Product> findByCategoryId(String cid, Pageable pageable); // Phương thức phân trang theo danh mục

    Product create(Product product);

    Product update(Product product);

    void delete(Integer id);
}
