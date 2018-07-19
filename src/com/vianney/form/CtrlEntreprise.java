package com.vianney.form;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vianney.beans.Entreprise;
import com.vianney.dao.EntrepriseDao;

public class CtrlEntreprise {
	
	private Connection connection;
	private Entreprise entreprise;
	private boolean ok= true;
	private String msgErrNom;
	private String classeNom;
	private String msgErrAdresse;
	private String classeAdresse;
	private String msgErrVille;
	private String classeVille;
	private String msgErrCp;
	private String classeCp;

	public CtrlEntreprise(Connection uConnection) {
		connection= uConnection;
		entreprise= new Entreprise();
	}
	
	public boolean ctrlNom(String nom) {
		entreprise.setNom(nom);
		EntrepriseDao newDao= new EntrepriseDao(connection);
		
		if(!newDao.SelectByNom(nom)) {
			if(!( nom.length() < 5)) {
				classeNom= classe(true);
				return true;
			} else {
				msgErrNom= "le nom doit comporter plus de 6 caractéres";
			}
		} else {
			msgErrNom= "une entreprise avec le même nom existe déja";
		}
		
		classeNom= classe(false);
		ok= false;
		return false;
	}
	
	public boolean ctrlAdresse(String adresse) {
		entreprise.setAdresse(adresse);
		if (!(adresse.length() < 9)) {
			classeAdresse= classe(true);
			return true;
		} else {
			msgErrAdresse= "l'adresse doit comporter plus de 10 caractéres";
		}
		classeAdresse= classe(false);
		return false;
	}
	
	public boolean ctrlVille(String ville) {
		entreprise.setVille(ville);
		if(!(ville.length() < 2)) {
			classeVille= classe(true);
			return true;
		} else {
			msgErrVille= "la ville doit comporter plus de 3 caractéres";
		}
		classeVille= classe(false);
		return false;
	}
	
	public boolean ctrlCp(String cp) {
		try {
			entreprise.setCodePostal(Integer.parseInt(cp));
		} catch (Exception e) {
			
		}
		Pattern regexDate = Pattern.compile("^[0-9]{5}$", Pattern.CASE_INSENSITIVE);
		Matcher m = regexDate.matcher(cp);
		
		if (m.find()) {
			classeCp= classe(true);
			return true;
		} else {
			msgErrCp= "Le code postal n'est pas correct";
		}
		classeCp= classe(false);
		return false;
	}
	
 	private String classe(boolean b) {
		if (b) {
			return "is-valid";
		} else {
			return "is-invalid";
		}
	}

	public boolean isOk() {
		return ok;
	}

	public String getMsgErrNom() {
		return msgErrNom;
	}

	public String getClasseNom() {
		return classeNom;
	}

	public String getMsgErrAdresse() {
		return msgErrAdresse;
	}

	public String getClasseAdresse() {
		return classeAdresse;
	}

	public String getMsgErrVille() {
		return msgErrVille;
	}

	public String getClasseVille() {
		return classeVille;
	}

	public String getMsgErrCp() {
		return msgErrCp;
	}

	public String getClasseCp() {
		return classeCp;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}
	
	public boolean getOk() {
		return ok;
	}
	
}
