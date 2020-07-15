package com.app.banking.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.NoArgsConstructor;


/*@Getter
@Setter*/
@NoArgsConstructor
public class TransactionDto {
	private Integer transactionId;

    private BigDecimal amount;
    
    private Long toAccount;
    
    private Long fromAccount;
    
    private Timestamp transactionDateTime;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getToAccount() {
		return toAccount;
	}

	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}

	public Long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Timestamp getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(Timestamp transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
}
