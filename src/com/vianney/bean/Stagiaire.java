package com.vianney.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stagiaire {
	
	private String nom;
	private String msgErrNom;
	private String prenom;
	private String msgErrPrenom;
	private String email;
	private String msgErrEmail;
	private boolean ok= true;
	
	public String getNom() {
		return nom;
	}
	
	public boolean setNom(String nom) {
		if (!(nom.length() < 5) ) { 
			this.nom = nom;
			return true;
		} else {
			setMsgErrNom("Le nom doit comprendre plus de 6 caractères!");
			setOk();
			return false;
		}
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public boolean setPrenom(String prenom) {
		if (!(prenom.length() < 5) ) { 
			this.prenom = prenom;
			return true;
		} else {
			setMsgErrPrenom("Le prénom doit comprendre plus de 6 caractères!");
			setOk();
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
			setMsgErrEmail("L'adresse email n'est pas valide!");
			setOk();
			return false;
		}
	}

	public String getMsgErrNom() {
		return msgErrNom;
	}

	public void setMsgErrNom(String msgErrNom) {
		this.msgErrNom = msgErrNom;
	}

	public String getMsgErrPrenom() {
		return msgErrPrenom;
	}

	public void setMsgErrPrenom(String msgErrPrenom) {
		this.msgErrPrenom = msgErrPrenom;
	}

	public String getMsgErrEmail() {
		return msgErrEmail;
	}

	public void setMsgErrEmail(String msgErrEmail) {
		this.msgErrEmail = msgErrEmail;
	}

	public boolean getOk() {
		return ok;
	}

	public void setOk() {
		this.ok = false;
	}	
}