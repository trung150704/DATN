package com.mt.repository;

import org.springframework.stereotype.Repository;

import com.mt.entity.SizeProduct;
import com.mt.entity.SizeProductKey;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SizeProductRepository extends JpaRepository<SizeProduct, SizeProductKey> {}
