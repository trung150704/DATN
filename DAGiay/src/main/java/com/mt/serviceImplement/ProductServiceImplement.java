package com.mt.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mt.entity.Product;
import com.mt.repository.ProductRepository;
import com.mt.service.ProductService;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll(); // Lấy tất cả sản phẩm
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id).get(); // Lấy sản phẩm theo mã
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable); // Phương thức phân trang
    }

    @Override
    public Page<Product> findByCategoryId(String cid, Pageable pageable) {
        return productRepository.findByCategoryId(cid, pageable); // Phân trang theo danh mục
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product); // Tạo sản phẩm mới
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product); // Cập nhật sản phẩm
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id); // Xóa sản phẩm theo mã
    }

	@Override
	public List<Product> findByCategoryId(String cid) {
		return productRepository.findByCategoryId(cid);
	}

	@Override
	public Page<Product> findByPriceRange(double minPrice, double maxPrice, Pageable pageable) {
		  return productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
	}

	
}
