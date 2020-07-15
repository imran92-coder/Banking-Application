package com.app.banking.dto;



import java.math.BigDecimal;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountReqDto {
private Long fromAccountNumber;
	
	private BigDecimal amount;
	
	private Long toAccountNumber;

	public Long getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(Long fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(Long toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	
	


}

