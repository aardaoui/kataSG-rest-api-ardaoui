package com.sg.test.KataSG.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = BankAccount.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2439355159746130927L;

	private Long id;
	private String iban;
	@ToString.Exclude
	private Date creationDate;
	@ToString.Exclude
	private Client client;
	private BigDecimal solde;
	private BigDecimal maxSolde;
	@ToString.Exclude
	private List<AccountOperation> operations;

}
