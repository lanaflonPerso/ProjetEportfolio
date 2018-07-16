package com.vianney;

import java.util.Date;
import java.util.HashSet;

package com.vianney;

import java.util.Date;
import java.util.HashSet;

public class Certification {

	private HashSet competences;
	private int niveau;
	private int idFormation;
	private int idMetier;
	private Date annee;
	
	public Certification() {
		idFormation= 0;
		idMetier= 0;
		competences = new HashSet();
	}

	public HashSet getCompetences() {
		return competences;
	}

	public void setCompetences(int competences) {
		this.competences.add(competences);
	}

	public int getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}

	public int getIdMetier() {
		return idMetier;
	}

	public void setIdMetier(int idMetier) {
		this.idMetier = idMetier;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public Date getAnnee() {
		return annee;
	}

	public void setAnnee(Date annee) {
		this.annee = annee;
	}
}
