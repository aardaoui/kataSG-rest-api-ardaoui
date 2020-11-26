package com.sg.test.KataSG.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Client.class)

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6564323332394903261L;

	private Long id;

	private String firstName;

	private String lastName;

	private String useName;

	@ToString.Exclude
	private Date bornDate;

	private String mobil;

	private String email;

	@ToString.Exclude
	//@JsonManagedReference
	private Adresse adresse;

	@ToString.Exclude
	//@JsonBackReference
	private List<BankAccount> accounts;

}
