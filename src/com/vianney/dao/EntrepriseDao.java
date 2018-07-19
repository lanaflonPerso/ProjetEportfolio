package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Entreprise;

public class EntrepriseDao {
	
	private Connection connection;
	private List<Entreprise> entreprises= new ArrayList<>();
	private Entreprise entreprise;

	public EntrepriseDao(Connection connection) {
		this.connection = connection;
	}
	
	public Entreprise selectByMetier(long idMetier) {

		String sql= "SELECT E.Id AS IdEntreprise, E.Nom AS NomEntreprise, E.Adresse, ";
		sql+= "E.Ville, E.CodePostal ";
		sql+= "FROM Entreprises AS E, Metier_Entreprise AS ME, Metiers AS M ";
		sql+= "WHERE ME.IdMetier= ? AND ME.IdEntreprise= E.Id";
		try {
			PreparedStatement ps= initPs(sql, idMetier);
			ResultSet r= ps.executeQuery();
			unique(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entreprise;
	}
	
	public void selectByStagaire(long idStagiaire) {
		String sql= "SELECT E.Id AS IdEntreprise, E.Nom AS NomEntreprise, E.Adresse, ";
		sql+= "E.Ville, E.CodePostal, M.Id AS IdMetier, M.Function, ";
		sql+= "FROM Entreprises AS E, Metier_Entreprise AS ME, Metiers AS M, Stagiaire_Metier AS SM ";
		sql+= "WHERE SM.IdStagiaire= ? AND ME.IdMetier= SM.IdMetier AND ME.IdEntreprise= E.Id AND SM.IdMetier= M.Id";
		try {
			PreparedStatement ps= initPs(sql, idStagiaire);
			ResultSet r= ps.executeQuery();
			unique(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void unique(ResultSet r) {
		try {
			
			if(r.next()) {	
				r.first();
				while(r.next()) {
					entreprise= new Entreprise();
					entreprise.setId(r.getLong("IdEntreprise"));
					entreprise.setNom(r.getString("NomEntreprise"));
					entreprise.setAdresse(r.getString("Adresse"));
					entreprise.setVille(r.getString("Ville"));
					entreprise.setCodePostal(r.getInt("CodePostal"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 	public List<Entreprise> getEntreprises() {
		return entreprises;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	private PreparedStatement initPs(String sql, Object... objects) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			for ( int i = 0; i < objects.length; i++ ) {
				ps.setObject( i + 1, objects[i] );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
}
