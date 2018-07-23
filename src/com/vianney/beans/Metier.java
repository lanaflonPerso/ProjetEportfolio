package com.vianney.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.vianney.HelperDate;

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
	public void setDateEntree(int annee, int mois, int jour) {
	    LocalDate localDate = LocalDate.of(annee, mois, jour);
	    dateEntree = localDate;
	}
	public LocalDate getDateSortie() {
		
		return dateSortie;
	}
	public void setDateSortie(int annee, int mois, int jour) {
	    LocalDate localDate = LocalDate.of(annee, mois, jour);		
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
	
	public String getDateEString() {
		return HelperDate.formatDateFR(getDateEntree());
	}
	
	public String getDateSString() {
		return HelperDate.formatDateFR(getDateSortie());
	}
}
