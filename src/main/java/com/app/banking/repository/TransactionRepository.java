package com.app.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.banking.model.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
	List<Transaction> findTop5ByFromAccountOrToAccountOrderByTransactionDateTimeDesc(Long id,Long sid);
}
