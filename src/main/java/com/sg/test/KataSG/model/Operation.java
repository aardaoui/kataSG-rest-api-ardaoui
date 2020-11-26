package com.sg.test.KataSG.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3729654267573587710L;

	private Long id;

	private String code;

	private String Libelle;

	private String typeOperation;

	private String Commentaire;
}
