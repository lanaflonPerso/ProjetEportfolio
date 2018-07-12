package com.vianney.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vianney.bean.Stagiaire;

public class StagiaireDaoImpl implements StagiaireDao {
 
	private DAOFactory daoFactory;

	StagiaireDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void creer(Stagiaire stagiaire) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
    public Stagiaire trouver( String email ) throws DAOException {
        return null;
    }
    
    private static Stagiaire map( ResultSet resultSet ) throws SQLException {
    	Stagiaire stagiaire = new Stagiaire();
    	stagiaire.setId( resultSet.getLong( "id" ) );
    	stagiaire.setEmail( resultSet.getString( "email" ) );
    	stagiaire.setNom( resultSet.getString( "nom" ) );
        return stagiaire;
    }
}
