package com.sg.test.KataSG.services;

import org.springframework.stereotype.Service;

import com.sg.test.KataSG.model.AccountOperation;

@Service
public class AccountOperationServiceImpl implements AccountOperationService {

	
	public AccountOperation saveOperation(AccountOperation accountOperation) throws Exception  {
        	accountOperation.getBankAccount().setSolde(accountOperation.getBankAccount().getSolde().add(accountOperation.getAmount()));
        	return accountOperation;
	}

	public AccountOperation retrieveOperation(AccountOperation accountOperation) {
        	accountOperation.getBankAccount().setSolde(accountOperation.getBankAccount().getSolde().subtract(accountOperation.getAmount()));
        	return accountOperation;
 	}


}