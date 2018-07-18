package com.vianney.form;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.vianney.beans.Stagiaire;

public class ControleNewStagiaire {
	
	private Stagiaire stagiaire;
	private Boolean ok= true;
	private String msgErrNom;
	private String classeNom;
	private String msgErrPrenom;
	private String classePrenom;
	private String msgErrEmail;
	private String classeEmail;
	private String msgErrDdn;
	private String classeDdn;
	
	public ControleNewStagiaire(HttpServletRequest request) {
		stagiaire= new Stagiaire();
		controleNom(request.getParameter("nom"));
		controlePrenom(request.getParameter("prenom"));
		controleEmail(request.getParameter("email"));
		controleDdn(request.getParameter("ddn"));
		stagiaire.setCivilite(request.getParameter("civilite"));
	}
	
	private void controleNom(String nom) {
		stagiaire.setNom(nom);
		if (!(nom.length() < 5) ) { 
			classeNom= classe(true);
		} else {
			msgErrNom= "Le nom doit comprendre plus de 6 caractères!";
			ok= false;
			classeNom= classe(false);
		}
	}
		
	private void controlePrenom(String prenom) {
		stagiaire.setPrenom(prenom);
		if (!(prenom.length() < 5) ) { 
			classePrenom= classe(true);
		} else {
			msgErrPrenom= "Le prénom doit comprendre plus de 6 caractères!";
			ok= false;
			classePrenom= classe(false);
		}
	}
	
	private void controleEmail(String email) {
		stagiaire.setEmail(email);
		Pattern regexMail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = regexMail.matcher(email);
		if (m.find()) {
			classeEmail= classe(true);
		} else {
			msgErrEmail= "L'adresse email n'est pas valide!";
			ok= false;
			classeEmail= classe(false);
		}
	}
	
	private void controleDdn(String date) {
		Pattern regexDate = Pattern.compile("^[0-9]{1,2}/[0-9]{1,2}/[0-9]{2,4}$", Pattern.CASE_INSENSITIVE);
		Matcher m = regexDate.matcher(date);
		
		if (m.find()) {
			String[] my = date.split("/");
			
			if (Integer.parseInt(my[0]) <= 31 && Integer.parseInt(my[1]) <= 12 && Integer.parseInt(my[2]) <= 2005) {
				String d= my[2]+my[1]+my[0];
				stagiaire.setDateNaissance(d);
				LocalDate today = LocalDate.now();
				Period age= Period.between(stagiaire.getDateNaissance(), today);
				stagiaire.setAge(age);
				classeDdn= classe(true);
			} else {				setMsgErrDdn();
				classeDdn= classe(false);
			}
		} else {			setMsgErrDdn();
			classeDdn= classe(false);
		}
	}
		
	private void setMsgErrDdn() {
		msgErrDdn= "Format de date incorrect (07/06/79)";
		ok= false;
	}
	
	private String classe(boolean b) {
		if (b) {
			return "is-valid";
		} else {
			return "is-invalid";
		}
	}
	
	public boolean getOk() {
		return ok;
	}
	
	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public String getMsgErrNom() {
		return msgErrNom;
	}

	public String getClasseNom() {
		return classeNom;
	}

	public String getMsgErrPrenom() {
		return msgErrPrenom;
	}

	public String getClassePrenom() {
		return classePrenom;
	}

	public String getMsgErrEmail() {
		return msgErrEmail;
	}

	public String getClasseEmail() {
		return classeEmail;
	}

	public String getMsgErrDdn() {
		return msgErrDdn;
	}

	public String getClasseDdn() {
		return classeDdn;
	}
}
