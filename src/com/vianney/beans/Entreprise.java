package com.vianney.beans;

import java.util.ArrayList;
import java.util.List;

public class Entreprise {
	
	private long id;
	private String adresse;
	private String ville;
	private String nom;
	private int codePostal;
	private List<Metier> metier= new ArrayList<Metier>();
	
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
	public List<Metier> getMetier() {
		return metier;
	}
	public void setMetier(Metier metier) {
		this.metier.add(metier);
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
}
