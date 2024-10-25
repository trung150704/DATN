package com.mt.serviceImplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mt.entity.Product;
import com.mt.repository.ProductRepository;
import com.mt.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImplement.class);
    
    @Override
    public List<Product> findAll() {
        return productRepository.findAll(); // Lấy tất cả sản phẩm
    }

    @Override
    public Product findById(Integer id) {
        logger.info("Finding product by id: {}", id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            logger.info("Product found: {}", product.get());
        } else {
            logger.warn("Product not found for id: {}", id);
        }
        return product.orElse(null); // Trả về null nếu không tìm thấy
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable); // Phương thức phân trang
    }

    @Override
    public Page<Product> findByCategoryId(Integer cid, Pageable pageable) {
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
    public void delete(Integer id) { // Thay đổi kiểu id thành Integer
        productRepository.deleteById(id); // Xóa sản phẩm theo mã
    }

    @Override
    public List<Product> findByCategoryId(Integer cid) {
        return productRepository.findByCategoryId(cid);
    }

    @Override
    public Page<Product> findByPriceRange(double minPrice, double maxPrice, Pageable pageable) {
        return productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    }
}
