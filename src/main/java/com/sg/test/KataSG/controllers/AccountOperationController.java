package com.sg.test.KataSG.controllers;

import java.net.URI;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.test.KataSG.model.AccountOperation;
import com.sg.test.KataSG.model.TypeOperation;
import com.sg.test.KataSG.services.AccountOperationService;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class AccountOperationController {
    
	 @Autowired
	 private  AccountOperationService accountOperationService;
	 
	    private static final Logger log = LoggerFactory.getLogger(AccountOperationController.class);

	    private final ObjectMapper objectMapper;

	    private final HttpServletRequest request;

	    @org.springframework.beans.factory.annotation.Autowired
	    public AccountOperationController(ObjectMapper objectMapper, HttpServletRequest request) {
	        this.objectMapper = objectMapper;
	        this.request = request;
	    }    
	    
	    @PostMapping(path= "/saveOperation",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Object> saveOperation(
                @RequestBody AccountOperation accountOperation)  {
            try {           	
                log.info("test info log: "+ accountOperation.toString());
            	accountOperation.getOperation().setTypeOperation(TypeOperation.SAVE.name());
                AccountOperation accountOperationSaved =  accountOperationService.saveOperation(accountOperation);
                
                
                //Create resource location
                URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                            .path("/{id}")
                                            .buildAndExpand(accountOperationSaved.getId())
                                            .toUri();
                 
                //Send location in response
                return ResponseEntity.created(location).build();                
                            	
            } catch (Exception e) {    
                Throwable root = e;
                while (root != null && !(root instanceof SQLException)) {
                    root = root.getCause();
                }
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "", e);
            }
		}
	    
	    @PostMapping(path= "/retrieveOperation",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Object> retrieveOperation(
                @RequestBody AccountOperation accountOperation)  {
            try {           	
                log.info("test info log: "+ accountOperation.toString());
            	accountOperation.getOperation().setTypeOperation(TypeOperation.SAVE.name());
                AccountOperation accountOperationSaved =  accountOperationService.retrieveOperation(accountOperation);
                
                
                //Create resource location
                URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                            .path("/{id}")
                                            .buildAndExpand(accountOperationSaved.getId())
                                            .toUri();
                 
                //Send location in response
                return ResponseEntity.created(location).build();                
                            	
            } catch (Exception e) {    
                Throwable root = e;
                while (root != null && !(root instanceof SQLException)) {
                    root = root.getCause();
                }
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "", e);
            }
		}
	    

}
