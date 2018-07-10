package com.vianney;

import java.util.Date;

public class Entreprise {

	private String adresse;
	private String ville;
	private String nom;
	private int id;
	private Date dateEntree;
	private Date dateSortie;
	
	public Entreprise (String uNom, Date dE, Date dS) {
		nom= uNom;
		dateEntree= dE;
		dateSortie= dS;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
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
}
