package com.vianney.form;

import java.sql.Connection;

import com.vianney.beans.Metier;

public class CtrlMetier extends Ctrl{
	
	private Metier metier= new Metier();;
	private String msgErrFonction;
	private String classeFonction;
	private String msgErrDescription;
	private String classeDescription;
	private String msgErrDateEntree;
	private String classeDateEntree;
	private String msgErrDateSortie;
	private String classeDateSortie;

	public CtrlMetier(Connection uConnection) {
		super(uConnection);
	}
	
	public boolean ctrlFonction(String fonction) {
		metier.setFonction(fonction);
		if(fonction.length() > 4) {
			return true;
		}
		msgErrFonction= "La fonction doit avoir plus de 5 caractéres";
		ok= false;	
		classeDescription= classe(false);
		return false;
	}
	
	public boolean ctrlDescription(String description) {
		metier.setDescription(description);
		if(description.length() > 4) {
			return true;
		}
		msgErrDescription= "La description doit avoir plus de 5 caractéres";
		classeDescription= classe(false);
		ok= false;
		return false;
	}
	
	public boolean ctrlDateEntree(String date) {
		if (ctrlDate(date)) {
			String[] my = date.split("/");
			
			if (Integer.parseInt(my[0]) <= 31 && Integer.parseInt(my[1]) <= 12 && Integer.parseInt(my[2]) <= yearNow()) {
				metier.setDateEntree(Integer.parseInt(my[2]), Integer.parseInt(my[1]), Integer.parseInt(my[0]));
				return true;
			}
		}
		ok= false;
		msgErrDateEntree= "Format de date incorrect (07/06/79)";
		classeDateEntree= classe(false);
		return false;
	}
	
	public boolean ctrlDateSortie(String date) {
		if (ctrlDate(date)) {
			String[] my = date.split("/");
			if (Integer.parseInt(my[0]) <= 31 && Integer.parseInt(my[1]) <= 12 && Integer.parseInt(my[2]) <= yearNow()) {
				metier.setDateSortie(Integer.parseInt(my[2]), Integer.parseInt(my[1]), Integer.parseInt(my[0]));
				return true;
			}
		} 
		ok= false;
		msgErrDateSortie= "Format de date incorrect (07/06/79)";
		classeDateSortie= classe(false);
		return false;
	}
	
	public String msgErrString(){
		return msgErrFonction;
	}
	
	public Metier getMetier() {
		return metier;
	}

	public String getMsgErrFonction() {
		return msgErrFonction;
	}

	public String getMsgErrDescription() {
		return msgErrDescription;
	}

	public String getMsgErrDateEntree() {
		return msgErrDateEntree;
	}

	public String getClasseDateEntree() {
		return classeDateEntree;
	}

	public String getMsgErrDateSortie() {
		return msgErrDateSortie;
	}

	public String getClasseDateSortie() {
		return classeDateSortie;
	}

	public String getClasseFonction() {
		return classeFonction;
	}

	public String getClasseDescription() {
		return classeDescription;
	}
}
