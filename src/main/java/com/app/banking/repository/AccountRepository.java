package com.app.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.banking.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
	public Account findByAccountNumber(Long id);
	
	public Account findByCustomerId(Integer id);
	
}
