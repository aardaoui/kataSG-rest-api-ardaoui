package com.sg.test.KataSG.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sg.test.KataSG.Data;
import com.sg.test.KataSG.model.AccountOperation;
import com.sg.test.KataSG.model.Adresse;
import com.sg.test.KataSG.model.BankAccount;
import com.sg.test.KataSG.model.Client;
import com.sg.test.KataSG.model.Operation;
import com.sg.test.KataSG.model.TypeOperation;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { AccountOperationServiceImpl.class,BankAccountServiceImpl.class })
public class AccountOperationServiceImplTest {

	@Autowired
	AccountOperationService accountOperationService;
	
	@Autowired
	BankAccountService bankAccountService;

	Operation operationSave;
	Operation operationRetreive;
	Operation operationCheck;
	BankAccount bankAccount;
	AccountOperation accountOperationToCheck;

	private void initData() {
		Adresse adresse = new Adresse(Long.valueOf(1), "45 bis", "rue", "liberation", "75015", "Paris");

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

		operationSave = new Operation();
		operationSave.setId(Long.valueOf(1));
		operationSave.setTypeOperation(TypeOperation.SAVE.name());
		operationSave.setLibelle("save operation");

		operationRetreive = new Operation();
		operationRetreive.setId(Long.valueOf(2));
		operationRetreive.setTypeOperation(TypeOperation.RETRIEVE.name());
		operationRetreive.setLibelle("retrieve operation");

		operationCheck = new Operation();
		operationCheck.setId(Long.valueOf(3));
		operationCheck.setTypeOperation(TypeOperation.CHECK.name());
		operationCheck.setLibelle("Check operation");
		Date dateDuJour = new Date();
		bankAccount = new BankAccount(Long.valueOf(2), "01551133161651613", dateDuJour, client,
				new BigDecimal("10000.00"), new BigDecimal("100000.00"), null);
		accountOperationToCheck = new AccountOperation(Long.valueOf(3), operationCheck, new Date(), bankAccount, null);
		AccountOperation accountOperationToSave = new AccountOperation(Long.valueOf(1), operationSave, new Date(),
				bankAccount, new BigDecimal("500.00"));
		AccountOperation accountOperationToRetreive = new AccountOperation(Long.valueOf(2), operationRetreive,
				new Date(), bankAccount, new BigDecimal("300.00"));

		 List<AccountOperation> AccountOperations =
		 Arrays.asList(accountOperationToSave,accountOperationToRetreive,accountOperationToCheck);

		 bankAccount.setOperations(AccountOperations);

	}

	@Test
	public void testSaveOperationOK() throws Exception {
		initData();
		AccountOperation accountOperationToSaveOK = new AccountOperation(Long.valueOf(1), operationSave, new Date(),
				bankAccount, new BigDecimal("500.00"));
		AccountOperation savedAccountOperationOK = accountOperationService.saveOperation(accountOperationToSaveOK);
		Assert.assertEquals(new BigDecimal("10500.00"), savedAccountOperationOK.getBankAccount().getSolde());
	}

	@Test
	public void testSaveOperationKO()  throws Exception {
		initData();
		AccountOperation accountOperationToSaveKO = new AccountOperation(Long.valueOf(4), operationSave, new Date(),
				bankAccount, new BigDecimal("400.00"));
		AccountOperation savedAccountOperationKO = accountOperationService.saveOperation(accountOperationToSaveKO);
		Assert.assertNotEquals(new BigDecimal("10500.00"), savedAccountOperationKO.getBankAccount().getSolde());
	}

	@Test
	public void testRetreiveOperationOK()  throws Exception {
		initData();
		AccountOperation accountOperationToRetreiveOK = new AccountOperation(Long.valueOf(2), operationRetreive,
				new Date(), bankAccount, new BigDecimal("300.00"));
		AccountOperation retreivedAccountOperationOK = accountOperationService
				.retrieveOperation(accountOperationToRetreiveOK);
		Assert.assertEquals(new BigDecimal("9700.00"), retreivedAccountOperationOK.getBankAccount().getSolde());
	}

	@Test
	public void testRetreiveOperationKO()  throws Exception {
		initData();
		AccountOperation accountOperationToRetreiveKO = new AccountOperation(Long.valueOf(5), operationRetreive,
				new Date(), bankAccount, new BigDecimal("200.00"));
		AccountOperation retreivedAccountOperationKO = accountOperationService
				.retrieveOperation(accountOperationToRetreiveKO);
		Assert.assertEquals(new BigDecimal("9800.00"), retreivedAccountOperationKO.getBankAccount().getSolde());
	}

	@Test
	public void testCheckOperationOK()  throws Exception {
		Data data = new Data();
		BankAccount bankAccount = bankAccountService.getBankAccount(Long.valueOf(1));
		Assert.assertNotNull(bankAccount);
	}

	@Test
	public void testCheckOperationKO()  throws Exception {
		Data data = new Data();
		BankAccount bankAccount = bankAccountService.getBankAccount(Long.valueOf(2));
		Assert.assertNull(bankAccount);
	}

}
