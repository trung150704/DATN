package com.mt.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mt.entity.Account;
import com.mt.service.AccountService;

@CrossOrigin("*")
@RestController

public class AccountRestController {
	@Autowired
	AccountService accountService;

	 @GetMapping("/rest/accounts")
	    public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> isAdmin) {
	        // Kiểm tra giá trị của admin và trả về danh sách phù hợp
	        if (isAdmin.orElse(false)) {
	            return accountService.getAdministrators(); // Trả về danh sách admin nếu isAdmin là true
	        }
	        return accountService.findAll(); // Trả về tất cả tài khoản nếu isAdmin là false hoặc không được chỉ định
	    }
}