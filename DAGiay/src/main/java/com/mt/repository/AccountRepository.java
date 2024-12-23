package com.mt.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mt.entity.Account;



public interface AccountRepository extends JpaRepository<Account, String> {

	@Query("SELECT a FROM Account a WHERE a.role = true")
	List<Account> getAdministrators();
	
	
}