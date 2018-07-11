package com.vianney;

import java.util.HashSet;

public class Metier {

	private HashSet entreprises;
	private HashSet competences;
	public String codeRome;
	public String intituleMetier;
	
	public Metier(String uRome, String uIntituleMetier) {
		codeRome= uRome;
		intituleMetier= uIntituleMetier;
		competences = new HashSet();
		entreprises = new HashSet();
	}
	
	public HashSet getCompetences() {
		return competences;
	}

	public void addCompetences(int competence) {
		this.competences.add(competence);
	}
	
	public HashSet getEntreprises() {
		return entreprises;
	}

	public void addEntreprises(int entreprise) {
		this.entreprises.add(entreprise);
	}

	public String getCodeRome() {
		return codeRome;
	}

	public void setCodeRome(String codeRome) {
		this.codeRome = codeRome;
	}

	public String getIntituleMetier() {
		return intituleMetier;
	}

	public void setIntituleMetier(String intituleMetier) {
		this.intituleMetier = intituleMetier;
	}	
}
