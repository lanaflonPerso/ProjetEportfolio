package com.vianney.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {
	
	

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetEportfolio") ;

	    EntityManager em = emf.createEntityManager() ;

	    Stagiaires stagiaire = new Stagiaires() ;

	    stagiaire.setNom("Bailleux");

	    stagiaire.setPrenom("Vianney");
	    
	    stagiaire.setEmail("via@free.fr");

	    em.getTransaction().begin() ;

	    em.persist(stagiaire) ;

	    em.getTransaction().commit() ;
	}
}
