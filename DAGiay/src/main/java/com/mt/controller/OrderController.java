//package com.mt.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.mt.service.OrderService;
//
//@Controller
//public class OrderController {
//	@Autowired
//	OrderService orderService;
//	
//	@RequestMapping("/order/checkout")
//	public String payment(Model model) {
//		model.addAttribute("pageTitle","Thanh Toán");
//		return "order/checkout";
//	}
//	
	
//	@RequestMapping("/order/detail/{id}")
//	public String detail(@PathVariable("id") Long id, Model model) {
//		model.addAttribute("order", orderService.findById(id));
//		return "order/detail";
//	}
//}
