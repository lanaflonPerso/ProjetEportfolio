package com.vianney.dao;

import com.vianney.Utilisateur;

public interface UtilisateurDao {
	
	void creer( Utilisateur utilisateur ) throws DAOException;
	Utilisateur trouver( String email ) throws DAOException;
}
