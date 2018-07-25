package com.vianney.beans;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Stagiaire {

	private Long id;
	private boolean admin;
	private String nom;
	private String prenom;
	private String email;
	private LocalDate dateNaissance;
	private Period age;
	private String civilite;
	private String adresse;
	private List<Metier> metiers = new ArrayList<>();
	private List<Formation> formations= new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	
	public String getDdnToString() { 
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
      return formatter.format(dateNaissance);
	}
	
	public void setDateNaissance(LocalDate ddn) {
		this.dateNaissance = ddn;
		LocalDate today = LocalDate.now();
		Period age= Period.between(ddn, today);
		setAge(age);
	}
	public int getAge() {
		return age.getYears();
	}
	public void setAge(Period age) {
		this.age = age;
	}
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public List<Metier> getMetiers() {
		return metiers;
	}
	public void setListMetiers(Metier metiers) {
		this.metiers.add(metiers);
	}
	public void setMetiers(List<Metier> metiers) {
		this.metiers= metiers;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean uAdmin) {
		admin = uAdmin;
	}
	public List<Formation> getFormations() {
		return formations;
	}
	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
	
	public void setListFormation(Formation formation) {
		this.formations.add(formation);
	}
}