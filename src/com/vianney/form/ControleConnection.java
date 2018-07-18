package com.vianney.form;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.StagiairesDao;

public class ControleConnection {

	private boolean ok= true;
	private Stagiaire stagiaire;
	private String msgErreur;
	private String msgMdp;
	private String msgEmail;
	private Connection connection;
	
	public ControleConnection(Connection uConnection, String uMdp, String uEmail) {
		connection= uConnection;
		verifMdp(uMdp);
		verifmail(uEmail);
		verif(uEmail, uMdp);
	}
	
	private boolean verifMdp(String mdp) {
		if(mdp.equals("")) {
			msgMdp= "Vous devez saisir un mot de passe";
			System.out.println("mot de passe vide");
			ok = false;
			return false;
		} else {
			return true;
		}
	}
	
	private boolean verifmail(String email) {
		Pattern regexMail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = regexMail.matcher(email);
		if (m.find()) {
			return true;
		} else {
			System.out.println("mail invalie");
			msgEmail="Le Mail n'est pas valide";
			ok= false;
			return false;
		}
	}
	
	private boolean verif(String uEmail, String uMdp) {
		StagiairesDao conStagiaire= new StagiairesDao(connection);
		stagiaire= conStagiaire.SelectByEmailMdp(uMdp, uEmail);
		if(stagiaire.getEmail().equals("")) {
			msgErreur= "Email ou mot de passe invalide";
			System.out.println("l'utilisateur n'existe pas");
			ok= false;
			return false;
		} else {
			return true;
		}
	}

	public boolean isOk() {
		return ok;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public String getMsgErreur() {
		return msgErreur;
	}

	public String getMsgMdp() {
		return msgMdp;
	}

	public String getMsgEmail() {
		return msgEmail;
	}
}
