package com.vianney.form;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import com.vianney.beans.Certification;
import com.vianney.beans.Formation;

public class CtrlFormation extends Ctrl {
	
	private HttpServletRequest request;
	
	private Formation formation= new Formation();
	private Certification certification= new Certification();
	private boolean avecCertification;
	private String msgErrIntitule;
	private String classeIntitule;
	private String msgErrNomCertification;
	private String classeNomCertification;
	private String msgErrNiveau;
	private String classeNiveau;
	
	public CtrlFormation(Connection uConnection, HttpServletRequest request) {
		super(uConnection);
		this.request= request;
	}
	
	public void ctrl() {
		ctrlIntitule((String) request.getParameter("intitule"));
		ctrlNomCertification((String) request.getParameter("certification"));
		try {
			ctrlNiveau(Integer.parseInt(request.getParameter("niveau")));	
		} catch (Exception e) {
			
		}
		
		formation.setCertification(certification);
	}
	public boolean ctrlIntitule(String intitule) {
		if(intitule.length() < 7) {
			msgErrIntitule= "7 caractéres minimum";
			classeIntitule= classe(false);
			ok= false;
			return false;
		}
		formation.setIntituleFormation(intitule);
		return true;	
	}
	
	public boolean ctrlNomCertification(String nom) {
		if(nom.equals("")) {
			avecCertification= false;
			return true;
		} else if(nom.length() < 5) {
			msgErrNomCertification= "5 caractéres minimum";
			classeNomCertification= classe(false);
			ok= false;
			return false;
		}
		certification.setNom(nom);
		return true;
	}
	
	public boolean ctrlNiveau(int niveau) {
		if (niveau < 0 && niveau > 7) {
			msgErrNiveau= "La valeur est incorrect";
			classeNiveau= classe(false);
			ok= false;
			return false;
		}
		certification.setNiveau(niveau);
		return true;
	}

	public boolean isAvecCertification() {
		return avecCertification;
	}

	public String getMsgErrIntitule() {
		return msgErrIntitule;
	}

	public String getClasseIntitule() {
		return classeIntitule;
	}

	public String getMsgErrNomCertification() {
		return msgErrNomCertification;
	}

	public String getClasseNomCertification() {
		return classeNomCertification;
	}

	public String getMsgErrNiveau() {
		return msgErrNiveau;
	}

	public String getClasseNiveau() {
		return classeNiveau;
	}

	public Formation getFormation() {
		return formation;
	}

	public Certification getCertification() {
		return certification;
	}
}
