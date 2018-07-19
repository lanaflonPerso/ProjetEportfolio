package com.vianney.form;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.StagiairesDao;

public class ControleStagiaire {
	
	private Stagiaire stagiaire;
	private Connection connection;
	private boolean ok= true;
	private String msgMdpVs;
	private String msgErrNom;
	private String classeNom;
	private String msgErrPrenom;
	private String classePrenom;
	private String msgErrEmail;
	private String classeEmail;
	private String msgErrDdn;
	private String classeDdn;
	private String classeAdresse;
	private String msgErrAdresse;
	
	public ControleStagiaire(Connection uConnection) {
		connection= uConnection;
		stagiaire= new Stagiaire();
	}
	
	public boolean ctrlMdpVd(long id, String mdp1, String mdp2) {
		if(mdp1.equals(mdp2)) {
			if(mdp1.length() > 7) {
								
				StagiairesDao stagiaire= new StagiairesDao(connection);				
				stagiaire.changeMdp(id, mdp1);
				return true;
			} else {
				msgMdpVs= "le mot de passe doit être egale ou supérieur a 8 caractéres";
				ok= false;
				return false;
			}
		} else {
			msgMdpVs= "les mots de passe ne sont pas identique";
			ok= false;
			return false;
		}
	}

	public void ctrlNom(String nom) {
		stagiaire.setNom(nom);
		if (!(nom.length() < 5) ) { 
			classeNom= classe(true);
		} else {
			msgErrNom= "Le nom doit comprendre plus de 6 caractères!";
			ok= false;
			classeNom= classe(false);
		}
	}
	public void ctrlPrenom(String prenom) {
		stagiaire.setPrenom(prenom);
		if (!(prenom.length() < 5) ) { 
			classePrenom= classe(true);
		} else {
			msgErrPrenom= "Le prénom doit comprendre plus de 6 caractères!";
			ok= false;
			classePrenom= classe(false);
		}
	}
	public void ctrlEmail(String email, String emailUser) {
		stagiaire.setEmail(email);
		
		if (!email.equals(emailUser)) {
			Pattern regexMail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			Matcher m = regexMail.matcher(email);
			if (m.find()) {
				StagiairesDao sd= new StagiairesDao(connection);
				if(sd.VerifMail(email)) {
					msgErrEmail= "L'adresse email existe déja";
					ok= false;
					classeEmail= classe(false);
				}
				classeEmail= classe(true);
			} else {
				msgErrEmail= "L'adresse email n'est pas valide!";
				ok= false;
				classeEmail= classe(false);
			}
			classeEmail= classe(true);
		}
	}
	public void ctrlAdresse(String adresse) {
		stagiaire.setAdresse(adresse);
		if (!(adresse.length() < 9)) {
			classeAdresse= classe(true);
		} else {
			msgErrAdresse= "L'adresse doit comprendre plus de 10 caractères!";
			ok= false;
			classeAdresse= classe(false);
		}
	}
	private String classe(boolean b) {
		if (b) {
			return "is-valid";
		} else {
			return "is-invalid";
		}
	}
	public void ctrlDdn(String date) {
		Pattern regexDate = Pattern.compile("^[0-9]{1,2}/[0-9]{1,2}/[0-9]{2,4}$", Pattern.CASE_INSENSITIVE);
		Matcher m = regexDate.matcher(date);
		
		if (m.find()) {
			String[] my = date.split("/");
			
			if (Integer.parseInt(my[0]) <= 31 && Integer.parseInt(my[1]) <= 12 && Integer.parseInt(my[2]) <= 2005) {
				String d= my[2] +"-"+ my[1] +"-" +my[0];
				stagiaire.setDateNaissance(d);
				LocalDate today = LocalDate.now();
				Period age= Period.between(stagiaire.getDateNaissance(), today);
				stagiaire.setAge(age);
				classeDdn= classe(true);
			} else {
				msgErrDdn= "Format de date incorrect (07/06/79)";
				ok= false;
				classeDdn= classe(false);
			}
		} else {
			msgErrDdn= "Format de date incorrect (07/06/79)";
			ok= false;
			classeDdn= classe(false);
		}
	}
	public boolean isOk() {
		return ok;
	}
	public String getMsgMdpVs() {
		return msgMdpVs;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public Connection getConnection() {
		return connection;
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

	public String getClasseAdresse() {
		return classeAdresse;
	}

	public String getMsgErrAdresse() {
		return msgErrAdresse;
	}
	public void setId(long id) {
		stagiaire.setId(id);
	}
}
