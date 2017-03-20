package com.cassandrabankapp.util;


import org.springframework.stereotype.Component;

import com.cassandrabankapp.domain.Account;
import com.cassandrabankapp.dto.AccountForm;

@Component
public class AccountMapper {

	public Account map(AccountForm model) {
		Account domain = new Account();
		
		domain.setAccountNumber(model.getAccountNumber());
		domain.setAccountType(model.getAccountType());
		domain.setBalance(model.getBalance());
		
		return domain;
	}
	
	public AccountForm map(Account domain) {
		AccountForm model = new AccountForm();
		
		model.setAccountNumber(domain.getAccountNumber());
		model.setAccountType(domain.getAccountType());
		model.setBalance(domain.getBalance());
		
		return model;
	}
	
}

