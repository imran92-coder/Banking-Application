package com.app.banking.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.banking.dto.AccountReqDto;
import com.app.banking.dto.CustomerReqDto;
import com.app.banking.dto.TransactionDto;
import com.app.banking.dto.TransactionResponseDto;
import com.app.banking.model.Customer;
import com.app.banking.service.CustomerService;


@RestController
@RequestMapping("/services")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@PostMapping("")
	public ResponseEntity<String> register(@RequestBody CustomerReqDto customerReqDto) {
		String message = customerService.register(customerReqDto);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@GetMapping("LastTransactions/{id}")
	public TransactionResponseDto getLast5Transaction(@PathVariable String id) {
		Long longId = Long.parseLong(id);
		List<TransactionDto> transactionDtos = customerService.getLast5Transaction(longId);
		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setTransactionDos(transactionDtos);
		transactionResponseDto.setStatusMessage("Last 5 transactions retrived successfully");
		transactionResponseDto.setStatusCode(200);
		return transactionResponseDto;
	}

	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestBody AccountReqDto accountReqDto) {
		logger.info(String.valueOf(accountReqDto.getToAccountNumber()));
		String message = customerService.transfer(accountReqDto);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}
	
	@PostMapping("/phoneNumberBasedTransfer")
	public ResponseEntity<String> phoneNumberBasedTransfer(@PathVariable Long fromPhoneNumber,@PathVariable Long toPhoneNumber,@PathVariable BigDecimal amount) {
		AccountReqDto accountReqDto=new AccountReqDto();
		Long fromAccountNumber=customerService.getAccountbyPhone(fromPhoneNumber);
				Long toAccountNumber=customerService.getAccountbyPhone(toPhoneNumber);
				accountReqDto.setFromAccountNumber(fromAccountNumber);
				accountReqDto.setToAccountNumber(toAccountNumber);
				accountReqDto.setAmount(amount);
		String message = customerService.transfer(accountReqDto);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}
	
	@GetMapping("/getCustomer/{}")
	public Customer getCustomerId(@PathVariable Long phone) {
		return customerService.getCustomerbyPhone(phone);
		

	}

}
