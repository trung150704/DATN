package com.mt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mt.entity.Product;
import com.mt.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/product/list")
    public String list(
            Model model,
            @RequestParam(value = "cid", required = false) Optional<String> cid,
            @RequestParam(value = "p", defaultValue = "0") int page,
            @RequestParam(value = "sort", defaultValue = "name") String sort,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        // Tạo Sort và Pageable
        Sort sortOrder = Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable = PageRequest.of(page, 8, sortOrder);
        Page<Product> productPage;

        // Lấy dữ liệu sản phẩm từ dịch vụ
        if (cid.isPresent()) {
            productPage = productService.findByCategoryId(cid.get(), pageable);
        } else {
            productPage = productService.findAll(pageable);
        }

        // Thêm dữ liệu vào mô hình
        model.addAttribute("items", productPage.getContent());
        model.addAttribute("page", productPage);
        model.addAttribute("sort", sort);
        model.addAttribute("direction", direction);

        return "product/list";
    }

    @RequestMapping("/product/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Product item = productService.findById(id);
        model.addAttribute("item", item);
        return "product/detail";
    } 
}
