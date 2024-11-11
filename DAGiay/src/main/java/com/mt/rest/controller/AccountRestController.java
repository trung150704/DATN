package com.mt.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mt.entity.Account;

import com.mt.service.AccountService;

@CrossOrigin("*")
@Controller

public class AccountRestController {
	@Autowired
	AccountService accountService;
	@Autowired
	BCryptPasswordEncoder pe;
	 @GetMapping("/rest/accounts")
	    public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> isAdmin) {
	        // Kiểm tra giá trị của admin và trả về danh sách phù hợp
	        if (isAdmin.orElse(false)) {
	            return accountService.getAdministrators(); // Trả về danh sách admin nếu isAdmin là true
	        }
	        return accountService.findAll(); // Trả về tất cả tài khoản nếu isAdmin là false hoặc không được chỉ định
	    }
//	 @PostMapping
//		public Account save(@RequestBody Account user) {
//			return accountService.create(user);
//		}
//
//	 @PostMapping("/register")
//	    public String registerUser(Account user, Model model) {
//	       if (user.isRole() == false) {
//	    	   user.setRole(false); 
//	    	   accountService.create(user);    
//		}
//	       return "redirect:/security/login";
//	    }
	 @PostMapping("/register")
	 public String registerUser(Account user, RedirectAttributes redirectAttributes) {
	     if (!user.getPassword().equals(user.getConfirmPassword())) {
	         redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu xác nhận không khớp");
	         return "redirect:/register";
	     }
	     
	     if (!user.isRole()) {
	         user.setRole(false); 
	         accountService.create(user);
	         redirectAttributes.addFlashAttribute("message", "Đăng ký thành công! Vui lòng đăng nhập.");
	     }

	     System.out.println("Redirecting to /security/login/form");
	     return "redirect:/security/login/form"; 
	 }
	 @PostMapping("/change-password")
	    public String changePassword(@RequestParam String username,
	                                 @RequestParam String oldPassword,
	                                 @RequestParam String newPassword,
	                                 @RequestParam String confirmPassword,
	                                 RedirectAttributes redirectAttributes) {
	        // Kiểm tra xem mật khẩu mới và xác nhận có khớp hay không
	        if (!newPassword.equals(confirmPassword)) {
	            redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu mới và xác nhận không khớp!");
	            return "redirect:/security/repassword"; // Redirect về trang đổi mật khẩu
	        }

	        // Thay đổi mật khẩu
	        boolean result = accountService.changePassword(username, oldPassword, newPassword);
	        if (result) {
	            redirectAttributes.addFlashAttribute("message", "Đổi mật khẩu thành công!");
	            return "redirect:/product/list"; // Redirect đến danh sách sản phẩm
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Đổi mật khẩu thất bại! Kiểm tra mật khẩu cũ và thử lại.");
	            return "redirect:/security/repassword"; // Redirect về trang đổi mật khẩu
	        }
	    }
	 

	 @PostMapping("/update")
	 public String updateAccount(@ModelAttribute Account account, RedirectAttributes redirectAttributes) {
	     Account updatedAccount = accountService.updateAccount(account);
	     if (updatedAccount != null) {
	         redirectAttributes.addFlashAttribute("message", "Thông tin đã được cập nhật thành công!");
	     } else {
	         redirectAttributes.addFlashAttribute("errorMessage", "Không thể cập nhật thông tin!");
	     }
	     return "redirect:/product/list";
	 }
	 @PostMapping
	 public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
			// String fullname = oauth2.getPrincipal().getAtribute("name");
			String email = oauth2.getPrincipal().getAttribute("email");
			String password = Long.toHexString(System.currentTimeMillis());
			
			UserDetails user = User.withUsername(email)
					.password(pe.encode(password)).roles("cust").build();
			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

}