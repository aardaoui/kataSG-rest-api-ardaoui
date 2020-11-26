package com.sg.test.KataSG.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = AccountOperation.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOperation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9119658675122439073L;

	private Long id;

	
	private Operation operation;

	@ToString.Exclude
	private Date date;

	@ToString.Exclude
	private BankAccount bankAccount;

	private BigDecimal amount;

}
