package com.mt.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.entity.Account;
import com.mt.repository.AccountRepository;
import com.mt.service.AccountService;



@Service
public class AccountServiceImplement implements AccountService{
	@Autowired
	AccountRepository accountDAO;
	
	@Override
	public Account findById(String username) {
		Account account = accountDAO.findById(username).get();
		return account;
	}

	@Override
	public List<Account> getAdministrators() {
		return accountDAO.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		return accountDAO.findAll();
	}
}
