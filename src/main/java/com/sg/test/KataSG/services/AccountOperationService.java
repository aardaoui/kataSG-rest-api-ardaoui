package com.sg.test.KataSG.services;

import com.sg.test.KataSG.model.AccountOperation;

public interface AccountOperationService { 
	
	public AccountOperation saveOperation(AccountOperation accountOperation) throws Exception;
	
	public AccountOperation retrieveOperation(AccountOperation accountOperation) throws Exception;


}
