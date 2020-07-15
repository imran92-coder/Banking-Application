package com.app.banking.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.banking.dto.AccountReqDto;
import com.app.banking.dto.CustomerReqDto;
import com.app.banking.dto.TransactionDto;
import com.app.banking.model.Account;
import com.app.banking.model.Customer;
import com.app.banking.model.Transaction;
import com.app.banking.repository.AccountRepository;
import com.app.banking.repository.CustomerRepository;
import com.app.banking.repository.TransactionRepository;


@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;
	
	Logger logger = LoggerFactory.getLogger(CustomerService.class);

	public String register(CustomerReqDto customerReqDto) {
		Customer customer = new Customer();
		customer.setCustomerName(customerReqDto.getCustomerName());
		customer.setPassword(customerReqDto.getPassword());
		customerRepository.save(customer);
		Account account = new Account();

		Long number = generateRandom(12);
		account.setAccountNumber(number);
		if (account.getAmount() == null)
			account.setAmount(new BigDecimal(10000));
		customer = customerRepository.findByCustomerName(customerReqDto.getCustomerName());
		account.setCustomerId(customer.getCustomerId());
		logger.info("customer registered with ID " +customer.getCustomerId()+" and Account Number:"+number);

		accountRepository.save(account);
		return "customer with ID" +customer.getCustomerId()+ " registered successfully with our bank,account balence is 10000";

	}

	public String transfer(AccountReqDto accountReqDto) {

		BigDecimal amount = accountReqDto.getAmount();
		Account fromCustomer = accountRepository.findByAccountNumber(accountReqDto.getFromAccountNumber());
		Account toCustomer = accountRepository.findByAccountNumber(accountReqDto.getToAccountNumber());
       if(Optional.of(fromCustomer).isPresent()&&Optional.of(toCustomer).isPresent()) {
		if (fromCustomer.getAmount().compareTo(BigDecimal.ONE) > 0 && fromCustomer.getAmount().compareTo(amount) > 0) {
			fromCustomer.setAmount(fromCustomer.getAmount().subtract(amount));
			accountRepository.save(fromCustomer);
			toCustomer.setAmount(toCustomer.getAmount().add(amount));
			accountRepository.save(toCustomer);
			Transaction transaction = new Transaction();
			transaction.setFromAccount(fromCustomer.getAccountNumber());
			transaction.setAmount(amount);
			transaction.setToAccount(toCustomer.getAccountNumber());
			transaction.setTransactionDateTime(new Timestamp(System.currentTimeMillis()));
			transactionRepository.save(transaction);
			Transaction creditTransaction = new Transaction();
			creditTransaction.setToAccount(toCustomer.getAccountNumber());
			creditTransaction.setAmount(amount);
			creditTransaction.setFromAccount(fromCustomer.getAccountNumber());
			creditTransaction.setTransactionDateTime(new Timestamp(System.currentTimeMillis()));
			transactionRepository.save(creditTransaction);
			return "amount trasferred from " + accountReqDto.getFromAccountNumber() + " to "
					+ accountReqDto.getToAccountNumber();

		}
		return "insufficient balence to transfer amount,available balence is:"+fromCustomer.getAmount();
}
 return "Account not registered with our Bank";
	}


	public List<TransactionDto> getLast5Transaction(Long id) {
		List<Transaction> transactions = transactionRepository.findTop5ByFromAccountOrToAccountOrderByTransactionDateTimeDesc(id,id);
		return transactions.stream().map(s -> getDtoFromEntity(s)).collect(Collectors.toList());
	}
	
	public Long getAccountbyPhone(Long phone) {
		Customer customer= customerRepository.findByPhone(phone);
		return accountRepository.findByCustomerId(customer.getCustomerId()).getAccountNumber();
	}
	
	public Customer getCustomerbyPhone(Long phone) {
		return customerRepository.findByPhone(phone);
	}

	private TransactionDto getDtoFromEntity(Transaction transaction) {
		TransactionDto transactionDto = new TransactionDto();
		BeanUtils.copyProperties(transaction, transactionDto);
		return transactionDto;
	}

	public static long generateRandom(int length) {
		Random random = new Random();
		Set<Long> unique = new HashSet<>();
		char[] digits = new char[length];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < length; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		Long number = Long.parseLong(new String(digits));

		if (unique.contains(number))
			generateRandom(12);
		else
			return number;
		return number;

	}

}
