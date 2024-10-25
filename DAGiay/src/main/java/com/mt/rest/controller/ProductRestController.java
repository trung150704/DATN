package com.mt.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mt.entity.Product;
import com.mt.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

    @Autowired
    ProductService productService;

    // Lấy sản phẩm theo ID
    @GetMapping("{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") Integer id) {
        Product product = productService.findById(id);
        if (product != null) {
            System.out.println("Product found: " + product); // In ra sản phẩm tìm thấy
            return ResponseEntity.ok(product);
        } else {
            System.out.println("Product not found for id: " + id); // Nếu không tìm thấy, in ra lỗi
            return ResponseEntity.notFound().build();
        }
    }

    // Lấy tất cả sản phẩm
    @GetMapping
    public List<Product> getAll() {
        return productService.findAll(); // Gọi phương thức không phân trang
    }

    // Lấy tất cả sản phẩm có phân trang
    @GetMapping("/paged")
    public Page<Product> getAllPaged(Pageable pageable) {
        return productService.findAll(pageable); // Gọi phương thức phân trang
    }

    // Tạo sản phẩm mới
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    // Cập nhật sản phẩm
    @PutMapping("/{id}")
    public Product update(@PathVariable("id") Integer id, @RequestBody Product product) { // Thay đổi kiểu id thành Integer
        // Cần thiết lập mã sản phẩm cho đối tượng sản phẩm mới
        product.setId(id); // Đảm bảo mã sản phẩm trong đối tượng
        return productService.update(product);
    }

    // Xóa sản phẩm
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) { // Thay đổi kiểu id thành Integer
        productService.delete(id);
    }
}
