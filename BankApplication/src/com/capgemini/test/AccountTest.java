package com.capgemini.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

public class AccountTest {

	@Mock
	AccountRepository accountRepository;
	AccountService accountService = new AccountServiceImpl(accountRepository);
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testCreateAccountSufficientBalance() throws InsufficientInitialBalanceException {
		Account account=new Account();
		account.setAccountNumber(1234);
		account.setAmount(800);
		assertEquals(account,accountService.createAccount(1234, 800));
		
	}
	
	@Test(expected=InsufficientInitialBalanceException.class)
	public void testCreateAccountInsufficientBalance() throws InsufficientInitialBalanceException {
		accountService.createAccount(1334, 300);
		
	}

	@Test
	public void testShowBalanceValidAccountNumber() throws InvalidAccountNumberException {
		assertEquals(800,accountService.showBalance(1234));
	}
	
	@Test(expected=InvalidAccountNumberException.class)
	public void testShowBalanceInvalidAccountNumber() throws InvalidAccountNumberException {
		accountService.showBalance(1334);
		
	}



}
