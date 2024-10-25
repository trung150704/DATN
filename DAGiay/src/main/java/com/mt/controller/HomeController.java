package com.mt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping({"/", "/home/index" })
	public String home(Model model) {
		return "layout/body.html";
	}
	
	@RequestMapping({"/admin","/admin/home/index"})
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
	
}
