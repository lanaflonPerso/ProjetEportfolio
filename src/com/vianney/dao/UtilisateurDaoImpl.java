package com.vianney.dao;

import com.vianney.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
 
	private DAOFactory daoFactory;

	UtilisateurDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	@Override
    public Utilisateur trouver( String email ) throws DAOException {
        return null;
    }

    @Override
    public void creer( Utilisateur utilisateur ) throws IllegalArgumentException, DAOException {
    }
}
