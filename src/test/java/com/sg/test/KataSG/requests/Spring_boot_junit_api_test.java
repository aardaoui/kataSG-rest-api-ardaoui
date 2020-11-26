package com.sg.test.KataSG.requests;

import java.math.BigDecimal;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.test.KataSG.model.AccountOperation;
import com.sg.test.KataSG.model.Adresse;
import com.sg.test.KataSG.model.BankAccount;
import com.sg.test.KataSG.model.Client;
import com.sg.test.KataSG.model.Operation;
import com.sg.test.KataSG.model.TypeOperation;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class Spring_boot_junit_api_test {

	 AccountOperation accountOperationSave;
	 AccountOperation accountOperationRetreive;
	 AccountOperation accountCheck;
	 Client client;
     BankAccount bankAccount;
	 String saveOperationUrl;
	 String checkAccountnUrl;
	 String retrieveOperationUrl;
	 HttpHeaders headers;
	 ObjectMapper objectMapper;

	@Before
    public void setUp() {
	    saveOperationUrl = "http://localhost:8000/saveOperation";
	    checkAccountnUrl = "http://localhost:8000/checkAccount/{id}";
	    retrieveOperationUrl = "http://localhost:8000/retrieveOperation";

		 Adresse adresse = new Adresse(Long.valueOf(1),"45 bis","rue","liberation","75015","Paris"); 
		 
		 client = new Client();
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
		
		
		accountOperationSave = new AccountOperation(Long.valueOf(1), operationSave, new Date(), bankAccount, new BigDecimal("500.00"));
		accountOperationRetreive = new AccountOperation(Long.valueOf(2), operationRetreive, new Date(), bankAccount, new BigDecimal("300.00"));
		accountCheck = new AccountOperation(Long.valueOf(3), operationCheck, new Date(), bankAccount,null);
		List<AccountOperation> AccountOperations = Arrays.asList(accountOperationSave,accountOperationRetreive,accountCheck);
 
		bankAccount.setOperations(AccountOperations);
    }
	

    @Test
    public void testAccountOperationSave() throws Exception {
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
        HttpHeaders headers = new HttpHeaders(); 
        HttpEntity<AccountOperation> saveOperationRequest = new HttpEntity<AccountOperation>(accountOperationSave, headers);         
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

        ResponseEntity<String> result =  restTemplate.postForEntity(saveOperationUrl, saveOperationRequest, String.class);
        Assert.assertEquals(201, result.getStatusCodeValue());
	 }

	
    @Test
    public void testAccountOperationRetrieve() throws Exception {
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
        HttpHeaders headers = new HttpHeaders(); 
        HttpEntity<AccountOperation> retrieveOperationRequest = new HttpEntity<AccountOperation>(accountOperationRetreive, headers);         
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

        ResponseEntity<String> result =  restTemplate.postForEntity(retrieveOperationUrl, retrieveOperationRequest, String.class);
        Assert.assertEquals(201, result.getStatusCodeValue());
	 }
 
    @Test
    public void testAccountCheck() throws Exception {
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        
        Long id = Long.valueOf(1);
        ResponseEntity<Client> result = restTemplate.getForEntity(checkAccountnUrl, Client.class,id);
        //Verify request succeed
        Assert.assertNotNull(result);
        
        
	 }

}
