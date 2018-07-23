package com.vianney.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Metier {
	private long id;
	private LocalDate dateEntree;
	private LocalDate dateSortie;
	private String fonction;
	private String description;
	private List<Entreprise> entreprises= new ArrayList<>();
	private List<Competence> competences= new ArrayList<>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getDateEntree() {
		return dateEntree;
	}
	public void setDateEntree(int jour, int mois, int annee) {
	    LocalDate localDate = LocalDate.of(2012, 06, 05);
	    dateEntree = localDate;
	}
	public LocalDate getDateSortie() {
		
		return dateSortie;
	}
	public void setDateSortie(int jour, int mois, int annee) {
	    LocalDate localDate = LocalDate.of(2005, 12, 20);		
		dateSortie = localDate;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Competence> getCompetences() {
		return competences;
	}
	public void setCompetences(List<Competence> competence) {
		this.competences = competence;
	}
	
	public void setListCompetence(Competence c) {
		competences.add(c);
	}
	public List<Entreprise> getEntreprises() {
		return entreprises;
	}
	public void setEntreprises(List<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}
	public void setListEntreprises(Entreprise entreprise) {
		entreprises.add(entreprise);
	}
}
