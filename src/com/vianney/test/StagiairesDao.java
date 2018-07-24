package com.vianney.test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.DAOException;

@Stateless
public class StagiairesDao {

    private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Stagiaires u WHERE u.Email=:email";
    private static final String PARAM_EMAIL           = "email";

    @PersistenceContext( unitName = "ProjetEportfolio" )
    private EntityManager       em;

    public void creer( Stagiaire stagiaire ) throws DAOException {
        try {
            em.persist( stagiaire );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public Stagiaire trouver( String email ) throws DAOException {
        Stagiaire stagiaire = null;
        Query requete = em.createQuery( JPQL_SELECT_PAR_EMAIL );
        requete.setParameter( PARAM_EMAIL, email );
        try {
            stagiaire = (Stagiaire) requete.getSingleResult();
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return stagiaire;
    }
}
