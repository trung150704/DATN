package com.mt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mt.entity.Size;

public interface SizeService {
	List<Size> findAll();
}
