package com.vianney.form;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vianney.beans.Metier;
import com.vianney.beans.Stagiaire;

public class CtrlMetier extends Ctrl{
	
	private Metier metier= new Metier();
	private Stagiaire stagiaire;
	private String msgErrFonction;
	private String classeFonction;
	private String msgErrDescription;
	private String classeDescription;
	private String msgErrDate;
	private String classeDate;


	public CtrlMetier(Connection uConnection, Stagiaire uStagiaire) {
		super(uConnection);
		stagiaire= uStagiaire;
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
	
	public boolean ctrlDate(String dateEntree, String dateSortie) {
		int anneeN= stagiaire.getDateNaissance().getYear();
		
		if (ctrlDate(dateEntree) && ctrlDate(dateSortie)) {
		
			LocalDate dateE= enLocalDate(dateEntree);
			LocalDate dateS= enLocalDate(dateSortie);
	
			if(afterDateEntree(dateE, dateS)) {	
				if (dateE.getYear() > anneeN+16 ) {
					return true;
				} else {
					ok= false;
					msgErrDate= "vous ne pouvez travailler avant 16 ans";
					classeDate= classe(false);
				}
			} 
		} else {
			ok= false;
			msgErrDate= "Format de date incorrecte exemple: 20/05/2011";
			classeDate= classe(false);
		}
		return false;
	}
	
	private LocalDate enLocalDate(String date) {
		
		Pattern regexDate1 = Pattern.compile("^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$", Pattern.CASE_INSENSITIVE);
		Matcher m1 = regexDate1.matcher(date);
		
		Pattern regexDate2 = Pattern.compile("^[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}$", Pattern.CASE_INSENSITIVE);
		Matcher m2 = regexDate2.matcher(date);
		
		DateTimeFormatter formatter = null;
		
		if (m1.find()) {
			formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		} else if (m2.find()) {
			formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
		}
		
		try {
			LocalDate localDate = LocalDate.parse(date, formatter);
			return localDate;
		}catch (Exception e) {
			ok= false;
			msgErrDate= "Format de date incorrecte exemple: 20/05/2011";
			classeDate= classe(false);
			return now;
		}
	}
	
	private boolean afterDateEntree(LocalDate dateEntree, LocalDate dateSortie) {
		int moisE= dateEntree.getMonthValue();
		int anneeE= dateEntree.getYear();
		int moisS= dateSortie.getMonthValue();
		int anneeS= dateSortie.getYear();
		
		if (anneeE < anneeS) {
			return true;
		} else if (anneeE == anneeS && moisE < moisS) {
			return true;
		}
		msgErrDate= "les dates se chevauche";
		ok= false;
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

	public String getMsgErrDate() {
		return msgErrDate;
	}

	public String getClasseFonction() {
		return classeFonction;
	}

	public String getClasseDescription() {
		return classeDescription;
	}
}
