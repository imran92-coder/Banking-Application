package com.app.banking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerId =0;
	
	private String customerName;
	
	private String password;
	
	private Integer phone;
	
	private Integer aadhar;
	
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId ;
	}


	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Integer getAadhar() {
		return aadhar;
	}

	public void setAadhar(Integer aadhar) {
		this.aadhar = aadhar;
	}
}
