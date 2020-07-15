package com.app.banking.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.banking.dto.AccountReqDto;
import com.app.banking.dto.CustomerReqDto;
import com.app.banking.model.Account;
import com.app.banking.model.Customer;
import com.app.banking.model.Transaction;
import com.app.banking.repository.AccountRepository;
import com.app.banking.repository.CustomerRepository;
import com.app.banking.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private AccountRepository accountRepository;

	AccountReqDto accountReqDto = new AccountReqDto();

	Account fromAccount = new Account();

	@Before
	public void setup() {

		accountReqDto.setFromAccountNumber(667564543423L);
		accountReqDto.setToAccountNumber(327564543423L);
		accountReqDto.setAmount(new BigDecimal("1000"));

		fromAccount.setAccountNumber(667564543423L);
		fromAccount.setAmount(new BigDecimal("1001"));
		Account toAccount = new Account();
		toAccount.setAccountNumber(667564543423L);
		toAccount.setAmount(new BigDecimal("1000"));
		when(accountRepository.findByAccountNumber(accountReqDto.getFromAccountNumber())).thenReturn(fromAccount);
		when(accountRepository.findByAccountNumber(accountReqDto.getToAccountNumber())).thenReturn(toAccount);

	}

	@Test
	public void testRegister() {
		CustomerReqDto customerReqDto = new CustomerReqDto();
		customerReqDto.setCustomerName("TestCustomer");
		customerReqDto.setPassword("TestCustomer");
		Customer customer = new Customer();
		customer.setCustomerId(1);
		when(customerRepository.findByCustomerName(customerReqDto.getCustomerName())).thenReturn(customer);
		Assert.assertTrue(customerService.register(customerReqDto).contains("1 registered successfully"));

	}

	@Test
	public void testTransferSuccess() {

		String message = customerService.transfer(accountReqDto);
		Assert.assertEquals("amount trasferred from 667564543423 to 327564543423",message);

	}

	@Test
	public void testTransferFailure() {
		fromAccount.setAmount(new BigDecimal("500"));
		String message = customerService.transfer(accountReqDto);
		Assert.assertEquals("insufficient balence to transfer amount,available balence is:500",message);

	}

	@Test
	public void testGetLast5Transaction() {
		Transaction firstTransaction = new Transaction();
		Transaction secondTransaction = new Transaction();
		firstTransaction.setTransactionId(1);
		firstTransaction.setFromAccount(667564543423L);
		firstTransaction.setToAccount(327564543423L);
		secondTransaction.setTransactionId(1);
		secondTransaction.setFromAccount(667564543423L);
		secondTransaction.setToAccount(327564543423L);
		List<Transaction> transactions = Stream.of(firstTransaction, secondTransaction).collect(Collectors.toList());
		when(transactionRepository.findTop5ByFromAccountOrToAccountOrderByTransactionDateTimeDesc(1L, 1L))
				.thenReturn(transactions);
		Assert.assertEquals(customerService.getLast5Transaction(1L).size(), 2);
	}

}
