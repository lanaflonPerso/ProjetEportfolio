package com.vianney.form;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
				return true;
			}
						
			if (dateE.getYear() > anneeN+16 ) {
				return true;
			}
		}
		
		if (ctrlDate(date)) {
			String[] my = date.split("/");
			
			int jour= Integer.parseInt(my[0]);
			int mois= Integer.parseInt(my[1]);
			int annee= Integer.parseInt(my[2]);
			if (annee > stagiaire.getDateNaissance().getYear()+16) {
				if (jour <= 31 && mois <= 12 && annee <= yearNow()) {
					metier.setDateEntree(Integer.parseInt(my[2]), Integer.parseInt(my[1]), Integer.parseInt(my[0]));
					return true;
				}
			} else {
				ok= false;
				msgErrDate= "Date incoherente";
				classeDate= classe(false);
			}
		}
		ok= false;
		msgErrDate= "Format de date incorrect (07/06/79)";
		classeDate= classe(false);
		return false;
	}
	
	private LocalDate enLocalDate(String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			LocalDate localDate = LocalDate.parse(date, formatter);
			return localDate;
		} catch (Exception e) {
			ok= false;
			msgErrDate= "Format de date incorrect (07/06/79)";
			classeDate= classe(false);
		}
		return now;
	}
	
	private boolean afterDateEntree(LocalDate dateEntree, LocalDate dateSortie) {
		int moisE= dateEntree.getMonthValue();
		int anneeE= dateEntree.getYear();
		int moisS= dateSortie.getMonthValue();
		int anneeS= dateEntree.getYear();
		
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
