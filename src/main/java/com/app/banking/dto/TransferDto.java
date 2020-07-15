package com.app.banking.dto;

import java.math.BigDecimal;

public class TransferDto {
	    private String fromCustomerName;

	    private String toCustomerName;
	    
	    private String password;

	    private BigDecimal amount;

		public String getFromCustomerName() {
			return fromCustomerName;
		}

		public void setFromCustomerName(String fromCustomerName) {
			this.fromCustomerName = fromCustomerName;
		}

		public String getToCustomerName() {
			return toCustomerName;
		}

		public void setToCustomerName(String toCustomerName) {
			this.toCustomerName = toCustomerName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

	}

