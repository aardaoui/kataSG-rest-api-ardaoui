package com.sg.test.KataSG.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adresse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5455839917660617458L;

	private Long id;
	private String Number;
	private String typeVoix;
	private String nameVoix;
	private String postalCode;
	private String city;

}
