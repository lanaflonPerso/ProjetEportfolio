package com.vianney.form;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.StagiairesDao;

public class CtrlStagiaire extends Ctrl {
	
	private Stagiaire stagiaire= new Stagiaire();
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
	private String msgErreur;

	public CtrlStagiaire(Connection uConnection) {
		super(uConnection);
	}
	
	public void addStagiaire(HttpServletRequest request) {
		ctrlNom(request.getParameter("nom"));
		ctrlPrenom(request.getParameter("prenom"));
		ctrlEmail(request.getParameter("email"), true);
		ctrlDdn(request.getParameter("ddn"));
		stagiaire.setCivilite(request.getParameter("civilite"));
	}
	
	public boolean ctrlMdpVs(long id, String mdp1, String mdp2) {
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
	
	public boolean ctrlEmail(String email, boolean EmailExist) {
		stagiaire.setEmail(email);
		
		Pattern regexMail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = regexMail.matcher(email);
		if (m.find()) {
			if (EmailExist) {
				boolean ctrl= ctrlEmailExist(email);
				classeEmail= classe(ctrl);
				return ctrl;
			} else {
				classeEmail= classe(true);
				return true;
			}
		} else {
			msgErrEmail= "L'adresse email n'est pas valide!";
			ok= false;
			classeEmail= classe(false);
			return false;
		}
	}
	
	private boolean ctrlEmailExist(String email) {
		StagiairesDao sd= new StagiairesDao(connection);
		if(sd.SelectByMail(email)) {
			msgErrEmail= "L'adresse email existe déja";
			ok= false;
			classeEmail= classe(false);
			return true;
		}
		classeEmail= classe(true);
		return false;
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

	public void ctrlDdn(String date) {
		Pattern regexDate = Pattern.compile("^[0-9]{1,2}/[0-9]{1,2}/[0-9]{2,4}$", Pattern.CASE_INSENSITIVE);
		Matcher m = regexDate.matcher(date);
		
		if (m.find()) {
			String[] my = date.split("/");
			int jour= Integer.parseInt(my[0]);
			int mois= Integer.parseInt(my[1]);
			int annee= Integer.parseInt(my[2]);
						
			LocalDate now = LocalDate.now();
			if (annee <= now.getYear()-16) {
				try {
					LocalDate d= LocalDate.of(annee, mois, jour);
					stagiaire.setDateNaissance(d);
		
					Period age= Period.between(stagiaire.getDateNaissance(), now);
					stagiaire.setAge(age);
					classeDdn= classe(true);
				} catch (Exception e) {
					msgErrDdn= "Format de date incorrect (07/06/79)";
					ok= false;
					classeDdn= classe(false);
				}
			}
			else {
				msgErrDdn= "Le stagiaire doit avoir 16 ans minimum";
				ok= false;
				classeDdn= classe(false);
			}
		} else {
			msgErrDdn= "Format de date incorrect (07/06/79)";
			ok= false;
			classeDdn= classe(false);
		}
	}

	public boolean ctrlStagaireByEmailMdp(String email, String mdp) {
		StagiairesDao sDao= new StagiairesDao(connection);
		if(!sDao.SelectByEmailMdp(mdp, email)) {
			msgErreur= "Email ou mot de passe invalide";
			ok= false;
			return false;
		} else {
			stagiaire= sDao.getStagiaire();
			return true;
		}
	}
	
	public String getMsgMdpVs() {
		return msgMdpVs;
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

	public String getClasseAdresse() {
		return classeAdresse;
	}

	public String getMsgErrAdresse() {
		return msgErrAdresse;
	}
	
	public String getMsgErreur() {
		return msgErreur;
	}

 	public void setId(long id) {
		stagiaire.setId(id);
	}
}
