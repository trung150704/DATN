package com.mt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mt.dto.OrderRequest;
import com.mt.entity.Order;
import com.mt.entity.OrderDetail;
import com.mt.entity.Size;
import com.mt.service.OrderService;

@Controller
public class ShoppingCartController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/cart/view")
	public String view(Model model) {
		model.addAttribute("pageTitle","Giỏ Hàng");
		return "cart/view";
	}
	@RequestMapping("/cart/heart")
	public String heart(Model model) {
		model.addAttribute("pageTitle","Yêu Thích");
		return "cart/heart";
	}
	
	@RequestMapping("/cart/checkout")
	public String payment(Model model) {
	    OrderRequest orderRequest = new OrderRequest();
	    orderRequest.setOrder(new Order()); // Khởi tạo Order
	    orderRequest.setOrderDetails(new ArrayList<OrderDetail>()); // Khởi tạo danh sách OrderDetail
	    model.addAttribute("orderRequest", orderRequest);
	    model.addAttribute("pageTitle", "Thanh Toán");
	    return "cart/checkout";
	}


	@PostMapping("/cart/success")
	public String checkout(@ModelAttribute OrderRequest orderRequest, Model model) {
	    if (orderRequest.getOrder() == null || orderRequest.getOrderDetails() == null || orderRequest.getOrderDetails().isEmpty()) {
	        model.addAttribute("errorMessage", "Đơn hàng hoặc thông tin chi tiết đơn hàng không hợp lệ");
	        return "cart/checkout"; // Trả về trang checkout để người dùng có thể nhập lại
	    }

	    // Debugging logs
	    System.out.println("Order: " + orderRequest.getOrder());
	    System.out.println("Order details: " + orderRequest.getOrderDetails());

	    // Lưu đơn hàng
	    orderService.createOrder(orderRequest.getOrder(), orderRequest.getOrderDetails());
	    
	    // Thêm order vào mô hình
	    model.addAttribute("pageTitle", "Xác nhận đơn hàng");
	    model.addAttribute("order", orderRequest.getOrder());

	    // Chuyển hướng đến trang success
	    return "cart/success"; // Trả về trang thành công
	}





	
	@RequestMapping("/cart/saleoff")
	public String saleoff(Model model) {
		model.addAttribute("pageTitle","Giảm giá");
		return "cart/saleoff";
	}
}
