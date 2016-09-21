package com.capgemini.repository;

import com.capgemini.model.Account;

public interface AccountRepository {
	
	public boolean save(Account account);
	
	Account searchAccount(int accountNumber);

}
