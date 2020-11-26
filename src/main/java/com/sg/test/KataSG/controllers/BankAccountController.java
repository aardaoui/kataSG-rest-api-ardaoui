package com.sg.test.KataSG.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.test.KataSG.model.BankAccount;
import com.sg.test.KataSG.services.BankAccountService;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class BankAccountController {
    
	 @Autowired
	 private  BankAccountService bankAccountService;

	 
	    private static final Logger log = LoggerFactory.getLogger(BankAccountController.class);

	    private final ObjectMapper objectMapper;

	    private final HttpServletRequest request;

	    @org.springframework.beans.factory.annotation.Autowired
	    public BankAccountController(ObjectMapper objectMapper, HttpServletRequest request) {
	        this.objectMapper = objectMapper;
	        this.request = request;
	    }    
	    
	    @GetMapping(path= "/checkAccount/{id}")
	    public ResponseEntity<BankAccount> checkAccount(@PathVariable("id") Long id)  {
            try {           	
                log.info("test info log: id client  "+ id);
                BankAccount bankAccount = bankAccountService.getBankAccount(id);
                return new ResponseEntity<BankAccount>(bankAccount, HttpStatus.OK);
            } catch (Exception e) {    
                Throwable root = e;
                while (root != null && !(root instanceof SQLException)) {
                    root = root.getCause();
                }
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "", e);
            }
		}


}
