package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Competence;

public class CompetenceDao {
	
	private Connection connection;
	private List<Competence> competences= new ArrayList<>();
	private Competence competence;
	
	public CompetenceDao(Connection connection) {
		this.connection = connection;
	}
	
	public void selectCompetenceByMetier(long idMetier) {
		System.out.println("id=========================="+idMetier+"==========================metier");
		String sql= "SELECT C.Nom ";
		sql+= "FROM Metier_Competence AS MC, Competences AS C ";
		sql+= "WHERE MC.IdMetier= ? AND C.Id= MC.IdCompetence;";
		
		try {
			PreparedStatement ps= initPs(sql, idMetier);
			ResultSet r= ps.executeQuery();
			unique(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void unique(ResultSet r) {
		try {
			while(r.next()) {
				competence= new Competence();
				competence.setNom(r.getString("Nom"));
				
				System.out.println("=========================="+r.getString("Nom")+"==========================");
				
				competences.add(competence);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
 	public List<Competence> getCompetences() {
		return competences;
	}
 	
 	public Competence getCompetence() {
 		return competence;
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
