package com.vianney;

import java.time.LocalDateTime;

public class Utilisateur {

	protected String nom;
	protected String prenom;
	protected String adresse;
	protected int age;
	protected LocalDateTime dateNaissance;
	protected String email;
	
	public Utilisateur() {
		
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

	public String getDateNaissance() {
		int jour= dateNaissance.getDayOfMonth();		
		int mois= dateNaissance.getMonthValue();	
		int annee= dateNaissance.getYear();
		return jour+"/"+mois+"/"+annee;
	}

	public void setDateNaissance(String uDate) {		
		String[] my = uDate.split("/");
		int jour= Integer.parseInt(my[0]);		
		int mois= Integer.parseInt(my[1]);	
		int annee= Integer.parseInt(my[2]);
		setAge(jour, mois, annee);
		dateNaissance = LocalDateTime.of(annee, mois, jour, 0, 0, 0);
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	private void setAge(int jour, int mois, int annee) {
		LocalDateTime now= LocalDateTime.now();
		age= now.getYear()-annee;
		System.out.println(age);
		System.out.printf("%d < %d\n", now.getMonthValue(),  mois);
		System.out.printf("%d <= %d\n", now.getDayOfMonth(),  jour);
		if( now.getMonthValue() < mois) {
			age--;
		} else if (now.getMonthValue() == mois && now.getDayOfMonth() <= jour) {
			age++;
		}	
	}
	
	public int getAge() {
		return age;
	}
}
