package com.mt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mt.entity.Account;
import com.mt.service.AccountService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message","Vui lòng đăng nhập");
		return "security/login";
	}
	
	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message","Đăng nhập thành công");
		return "redirect:/product/list";
	}
	
	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message","Sai thông tin đăng nhập!");
		return "security/login";
	}
	
	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message","Không có quyền truy vấn~");
		return "security/login";
	}
	
	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message","Bạn đã đăng xuất");
		return "layout/body.html";
	}
	@RequestMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new Account());
	    return "security/register";
	}
	
	@RequestMapping("/repass")
	public String repassword() {
	    return "security/repassword";
	}
	@RequestMapping("/up")
	public String update() {
	    return "security/update";
	}
	@Autowired
    private AccountService accountService;
	
	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken token) {
	    accountService.processOAuth2Login(token);
	    return "redirect:/product/list"; // Điều hướng người dùng về trang sản phẩm sau khi đăng nhập thành công
	}
	

}
