package com.app.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.banking.model.Account;


public interface AccountRepository extends JpaRepository<Account,Integer> {
	public Account findByAccountNumber(Long id);
}
