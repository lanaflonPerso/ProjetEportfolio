package com.vianney.form;

import java.sql.Connection;

import com.vianney.beans.Metier;

public class CtrlMetier extends Ctrl{
	
	private Metier metier;
	private String msgErrFonction;

	public CtrlMetier(Connection uConnection, Metier metier) {
		super(uConnection);
		metier= new Metier();
	}
	
	public boolean ctrlFonction(String fonction) {
		if(fonction.length() > 4) {
			return true;
		}
		msgErrFonction= "La fonction doit avoir plus de 5 caractéres";
		ok= false;
		return false;
	}
}
