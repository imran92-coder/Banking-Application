package com.app.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.banking.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByCustomerId(Integer id);
	
	public Customer findByPhone(Long id);
	
	public Customer findByCustomerName(String name);

}
