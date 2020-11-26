package com.sg.test.KataSG.services;

import org.springframework.stereotype.Service;

import com.sg.test.KataSG.model.BankAccount;

public interface BankAccountService {
	BankAccount getBankAccount(Long idClient);
}
