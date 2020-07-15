package com.app.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.banking.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByCustomerId(Integer id);
	
	public Customer findByCustomerName(String name);

}
