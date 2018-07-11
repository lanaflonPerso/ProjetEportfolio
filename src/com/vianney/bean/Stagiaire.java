package com.vianney.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stagiaire {
	
	private String nom;
	private String prenom;
	private String email;
	
	public String getNom() {
		return nom;
	}
	public boolean setNom(String uNom) {
		if (uNom.length() < 5) {
			this.nom = uNom;
			return true;
		} else {
			return false;
		}
	}
	public String getPrenom() {
		return prenom;
	}
	public boolean setPrenom(String uPrenom) {
		if (uPrenom.length() < 5) {
			this.prenom = uPrenom;
			return true;
		} else {
			return false;
		}
	}
	public String getEmail() {
		return email;
	}
	public boolean setEmail(String uEmail) {
		Pattern regexMail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = regexMail.matcher(uEmail);

		if (m.find()) {
			email= uEmail;
			return true;
		} else {
			return false;
		}
	}
}