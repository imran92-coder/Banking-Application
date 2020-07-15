package com.app.banking.controller;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.banking.dto.AccountReqDto;
import com.app.banking.dto.CustomerReqDto;
import com.app.banking.dto.TransactionDto;
import com.app.banking.dto.TransactionResponseDto;
import com.app.banking.service.CustomerService;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	@InjectMocks
	CustomerController customerController;
	@Mock
	CustomerService customerService;

	@Test
	public void testRegister() {
		CustomerReqDto customerReqDto = new CustomerReqDto();
		customerReqDto.setCustomerName("test");
		customerReqDto.setPassword("test");
		String message = "customer with ID 1 registered successfully with our bank,account balence is 10000";
		when(customerService.register(customerReqDto)).thenReturn(message);
		ResponseEntity<String> responseEntity = customerController.register(customerReqDto);
		Assert.assertEquals(201, responseEntity.getStatusCodeValue());
		Assert.assertEquals(message, responseEntity.getBody());
	}

	@Test
	public void testGetLast5Transaction() {
		TransactionDto firstTransaction = new TransactionDto();
		TransactionDto secondTransaction = new TransactionDto();
		firstTransaction.setTransactionId(1);
		firstTransaction.setFromAccount(667564543423L);
		firstTransaction.setToAccount(327564543423L);
		secondTransaction.setTransactionId(1);
		secondTransaction.setFromAccount(667564543423L);
		secondTransaction.setToAccount(327564543423L);
		List<TransactionDto> transactions = Stream.of(firstTransaction, secondTransaction).collect(Collectors.toList());
		when(customerService.getLast5Transaction(1L)).thenReturn(transactions);
		TransactionResponseDto transactionResponseDto = customerController.getLast5Transaction("1");
		Assert.assertEquals("Last 5 transactions retrived successfully", transactionResponseDto.getStatusMessage());
	}

	@Test
	public void testTransfer() {
		AccountReqDto accountReqDto = new AccountReqDto();
		accountReqDto.setFromAccountNumber(868576456433L);
		accountReqDto.setToAccountNumber(968576456433L);
		accountReqDto.setAmount(new BigDecimal("8770"));
		when(customerService.transfer(accountReqDto)).thenReturn("amount trasferred from 868576456433L to 968576456433L");
		Assert.assertEquals(HttpStatus.OK, customerController.transfer(accountReqDto).getStatusCode());
	}

}
