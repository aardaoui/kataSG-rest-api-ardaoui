package com.sg.test.KataSG.services;

import org.springframework.stereotype.Service;

import com.sg.test.KataSG.Data;
import com.sg.test.KataSG.model.BankAccount;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	public BankAccount getBankAccount(Long idClient) {
		Data data = new Data();
		if (data.bankAccount.getClient().getId().equals(idClient))
			return Data.bankAccount;
		else 
			return null;
			
	}

}
