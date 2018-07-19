package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vianney.beans.Competence;
import com.vianney.beans.Entreprise;
import com.vianney.beans.Metier;

public class CompetenceDao {
	
	private Connection connection;
	private Competence competence;
	
	public CompetenceDao(Connection connection) {
		this.connection = connection;
	}
	
	public void selectCompetenceByMetier(String idMetier) {
		String sql= "C.Nom ";
		sql+= "FROM Metier_Comptence AS MC, Competence AS C ";
		sql+= "WHERE MC.IdMetier= ? AND C.Id= MC.IdCompetence;";
		try {
			PreparedStatement ps= initPs(sql, idMetier);
			ResultSet r= ps.executeQuery();
			unique(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		return Competence;
	}
	}
	
	private void unique(ResultSet r, Metier metier) {
		try {
			if(r.next()) {
				competence= new Competence();
				competence.setId(r.getString("Nom"));
				entreprise.setNom(r.getString("NomEntreprise"));
				entreprise.setAdresse(r.getString("Adresse"));
				entreprise.setVille(r.getString("Ville"));
				entreprise.setCodePostal(r.getInt("CodePostal"));
				entreprise.setListMetier(metier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
