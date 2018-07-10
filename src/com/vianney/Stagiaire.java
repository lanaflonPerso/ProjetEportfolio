package com.vianney;

import java.util.HashSet;
import java.util.Iterator;

public class Stagiaire extends Utilisateur {
	
	private int numeroStagiaire;
	private HashSet<Metier> metiers;

	public Stagiaire(String uNom) {
		super(uNom);
		metiers = new HashSet<Metier>();
	}

	public int getNumeroStagiaire() {
		return numeroStagiaire;
	}

	public void setNumeroStagiaire(int numeroStagiaire) {
		this.numeroStagiaire = numeroStagiaire;
	}

	public HashSet<Metier> getMetier() {
		return metiers;
	}

	public void addMetier(String uRome, String uIntituleMetier) {
		Metier r= new Metier(uRome, uIntituleMetier);
//		System.out.printf("%s - %s\n", uRome, uIntituleMetier);
		metiers.add(r);
	}
	
	public void effacerMetier(String uRecherhe) {
		boolean trouve= false;
		Iterator<Metier> e = metiers.iterator();
		while(e.hasNext() && !trouve) {
			if (((Metier)e.next()).getIntituleMetier() == uRecherhe) {
				trouve = true;
				e.remove();
			}	
		}
	}
	
	public void listerMetier() {
		for(Object s : metiers) {
			System.out.printf("code rome: %s intitulé: %s\n", ((Metier) s).getIntituleMetier(), ((Metier) s).getCodeRome());
		}  
	}
	
	private Metier rechercheMetier(HashSet<Metier> hs, String mot) {
		Metier result = null;
		boolean trouve= false;
		Iterator<Metier> e = hs.iterator();
		while(e.hasNext() && !trouve) {
			if (((Metier) e.next()).getIntituleMetier() == mot) {
				trouve = true;
				result= (Metier) e;
			}	
		}
		return result;
	}
}