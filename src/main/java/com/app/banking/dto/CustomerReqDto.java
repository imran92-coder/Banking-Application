package com.app.banking.dto;

import java.math.BigDecimal;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerReqDto {
private int customerId;
	
	private String customerName;
	
	private BigDecimal amount;
	
	private String password;
	
	private int toAccountId;
	
	private Long phone;
	
	private Integer aadhar;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Integer getAadhar() {
		return aadhar;
	}

	public void setAadhar(Integer aadhar) {
		this.aadhar = aadhar;
	}
	
}

