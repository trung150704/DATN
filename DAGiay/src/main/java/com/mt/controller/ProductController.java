package com.mt.controller;

import java.util.List;
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
import com.mt.entity.Size;
import com.mt.service.ProductService;
import com.mt.service.SizeService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SizeService sizeService;

    @RequestMapping("/product/list")
    public String list(
            Model model,
            @RequestParam(value = "cid", required = false) Optional<Integer> cid,
            @RequestParam(value = "p", defaultValue = "0") int page,
            @RequestParam(value = "sort", defaultValue = "name") String sort,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            @RequestParam(value = "priceFilter", required = false) Optional<String> priceFilter,
            @RequestParam(value = "priceDirection", required = false) Optional<String> priceDirection) {

        Sort sortOrder;
        if (priceDirection.isPresent()) {
            sortOrder = Sort.by(Sort.Direction.fromString(priceDirection.get()), "price");
        } else {
            sortOrder = Sort.by(Sort.Direction.fromString(direction), sort);
        }
        Pageable pageable = PageRequest.of(page, 21, sortOrder);
        Page<Product> productPage;

        if (priceFilter.isPresent()) {
            if (priceFilter.get().equals("greater")) {
                productPage = productService.findByPriceRange(500.0, Double.MAX_VALUE, pageable);
            } else if (priceFilter.get().equals("less")) {
                productPage = productService.findByPriceRange(0.0, 500.0, pageable);
            } else {
                productPage = productService.findAll(pageable);
            }
        } else if (cid.isPresent()) {
            productPage = productService.findByCategoryId(cid.get(), pageable);
        } else {
            productPage = productService.findAll(pageable);
        }

        model.addAttribute("items", productPage.getContent());
        model.addAttribute("page", productPage);
        model.addAttribute("sort", sort);
        model.addAttribute("direction", direction);
        model.addAttribute("priceFilter", priceFilter.orElse(""));
        model.addAttribute("priceDirection", priceDirection.orElse(""));
        model.addAttribute("pageTitle","Trang Sản Phẩm");
        return "product/list";
    }

    @RequestMapping("/product/ListNam")
    public String ListNam(
            Model model,
            @RequestParam(value = "p", defaultValue = "0") int page,
            @RequestParam(value = "sort", defaultValue = "name") String sort,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        Sort sortOrder = Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable = PageRequest.of(page, 15, sortOrder);

        Page<Product> productPage = productService.findByCategoryId(1, pageable); // Thay "DM01" bằng ID số nguyên

        model.addAttribute("items", productPage.getContent());
        model.addAttribute("page", productPage);
        model.addAttribute("sort", sort);
        model.addAttribute("direction", direction);

        return "product/ListNam";
    }

    @RequestMapping("/product/ListNu")
    public String ListNu(
            Model model,
            @RequestParam(value = "p", defaultValue = "0") int page,
            @RequestParam(value = "sort", defaultValue = "name") String sort,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        Sort sortOrder = Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable = PageRequest.of(page, 15, sortOrder);

        Page<Product> productPage = productService.findByCategoryId(2, pageable); // Thay "DM02" bằng ID số nguyên

        model.addAttribute("items", productPage.getContent());
        model.addAttribute("page", productPage);
        model.addAttribute("sort", sort);
        model.addAttribute("direction", direction);

        return "product/ListNu";
    }

    @RequestMapping("/product/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Product item = productService.findById(id);
        List<Size> sizes = sizeService.findAll();
        model.addAttribute("item", item);
        model.addAttribute("sizes", sizes);
        model.addAttribute("pageTitle", item.getName());
        return "product/detail";
    }
}
