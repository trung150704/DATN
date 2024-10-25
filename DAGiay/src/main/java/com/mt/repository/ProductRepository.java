package com.mt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mt.entity.Product;
import com.mt.entity.SizeProduct;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("SELECT p FROM Product p WHERE p.category.id = ?1")
	List<Product> findByCategoryId(Integer cid);

	 
	Page<Product> findByCategoryId(Integer cid, Pageable pageable);

	Page<Product> findByPriceBetween(double minPrice, double maxPrice, Pageable pageable);
}
