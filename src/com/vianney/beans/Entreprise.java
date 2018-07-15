package com.vianney.beans;

import java.time.LocalDate;

public class Entreprise {
	private long id;
	private String adresse;
	private String ville;
	private String nom;
	private LocalDate dateEntree;
	private LocalDate dateSortie;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public LocalDate getDateEntree() {
		return dateEntree;
	}
	public void setDateEntree(LocalDate dateEntree) {
		this.dateEntree = dateEntree;
	}
	public LocalDate getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
	}
}
