package com.vianney.dao;


import com.vianney.bean.Stagiaire;

public interface StagiaireDao {
	
	void creer( Stagiaire stagiaire ) throws DAOException;
	Stagiaire trouver( String email ) throws DAOException;
}
