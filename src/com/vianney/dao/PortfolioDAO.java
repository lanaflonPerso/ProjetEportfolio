package com.vianney.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Metier;
import com.vianney.beans.Stagiaire;

public class PortfolioDAO {
	
	private Stagiaire stagiaire;
	private List<Metier> metiers= new ArrayList<>();

	public PortfolioDAO(Connection connection, long id) {		
		StagiairesDao sDao= new StagiairesDao(connection);
		stagiaire= sDao.SelectById(id);
		
		MetierDao mDao= new MetierDao(connection);
		metiers= mDao.SelectByStagiaire(id);
		
		EntrepriseDao eDao= new EntrepriseDao(connection);
		
		for (Metier metier : metiers) {
			CompetenceDao cDao= new CompetenceDao(connection);
			metier.setListEntreprises(eDao.selectByMetier(metier.getId()));
			cDao.selectCompetenceByMetier(metier.getId());
			metier.setCompetences(cDao.getCompetences());
		
			stagiaire.setListMetiers(metier);
		}
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}
}
