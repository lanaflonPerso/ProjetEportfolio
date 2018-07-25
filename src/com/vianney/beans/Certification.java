package com.vianney.beans;

import java.util.ArrayList;
import java.util.List;

public class Certification {
	
	private long id;
	private String Nom;
	private int Niveau;
	private List<Competence> competences= new ArrayList<>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public int getNiveau() {
		return Niveau;
	}
	public void setNiveau(int niveau) {
		Niveau = niveau;
	}
	public List<Competence> getCompetences() {
		return competences;
	}
	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}
}
