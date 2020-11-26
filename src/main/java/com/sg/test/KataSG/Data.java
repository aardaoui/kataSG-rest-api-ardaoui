package com.sg.test.KataSG;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sg.test.KataSG.model.AccountOperation;
import com.sg.test.KataSG.model.Adresse;
import com.sg.test.KataSG.model.BankAccount;
import com.sg.test.KataSG.model.Client;
import com.sg.test.KataSG.model.Operation;
import com.sg.test.KataSG.model.TypeOperation;

public class Data {

	public static BankAccount bankAccount;
	
	public Data() {
		 Adresse adresse = new Adresse(Long.valueOf(1),"45 bis","rue","liberation","75015","Paris"); 
		 
		 Client client = new Client();
		 client.setId(Long.valueOf(1));
		 client.setFirstName("Ahmed");
		 client.setLastName("ARDAOUI");
		 String pattern = "MM-dd-yyyy";
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		 try {
			client.setBornDate(simpleDateFormat.parse("18-02-1980"));
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		client.setAdresse(adresse);
		client.setEmail("ardaoui@mail.com"); 
		client.setMobil("0606060606");

		Operation operationSave = new Operation();
		operationSave.setId(Long.valueOf(1));
		operationSave.setTypeOperation(TypeOperation.SAVE.name());
		operationSave.setLibelle("save operation");
		
		Operation operationRetreive = new Operation();
		operationRetreive.setId(Long.valueOf(2));
		operationRetreive.setTypeOperation(TypeOperation.RETRIEVE.name());
		operationRetreive.setLibelle("retrieve operation");

		Operation operationCheck = new Operation();
		operationCheck.setId(Long.valueOf(3));
		operationCheck.setTypeOperation(TypeOperation.CHECK.name());
		operationCheck.setLibelle("Check operation");
		Date dateDuJour  = new Date();
		bankAccount =  new BankAccount(Long.valueOf(2), "01551133161651613" , dateDuJour, client,new BigDecimal("10000.00"),new BigDecimal("100000.00"),null);
		
		
		AccountOperation accountOperationSave = new AccountOperation(Long.valueOf(1), operationSave, new Date(), bankAccount, new BigDecimal("500.00"));
		AccountOperation accountOperationRetreive = new AccountOperation(Long.valueOf(2), operationRetreive, new Date(), bankAccount, new BigDecimal("300.00"));
		AccountOperation accountOperationCheck = new AccountOperation(Long.valueOf(3), operationCheck, new Date(), bankAccount,null);
		List<AccountOperation> AccountOperations = Arrays.asList(accountOperationSave,accountOperationRetreive,accountOperationCheck);

		bankAccount.setOperations(AccountOperations);
	}

}
