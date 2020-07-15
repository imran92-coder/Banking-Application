package com.app.banking.dto;

import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {
List<TransactionDto> transactionDos = new ArrayList<>();
	
	private int statusCode;
	
	private String statusMessage;

	public List<TransactionDto> getTransactionDos() {
		return transactionDos;
	}

	public void setTransactionDos(List<TransactionDto> transactionDos) {
		this.transactionDos = transactionDos;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	
	
}