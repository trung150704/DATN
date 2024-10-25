package com.mt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mt.entity.Order;
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
		model.addAttribute("pageTitle","Thanh Toán");
		return "cart/checkout";
	}
	
	@RequestMapping("/cart/success")
	public String checkout(Model model) {
		//Order order = orderService.findById(id);
		//model.addAttribute("order",order);
		model.addAttribute("pageTitle","Xác nhận đơn hàng");
		return "cart/success";
	}
	
	@RequestMapping("/cart/saleoff")
	public String saleoff(Model model) {
		model.addAttribute("pageTitle","Giảm giá");
		return "cart/saleoff";
	}
}
