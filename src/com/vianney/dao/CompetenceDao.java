package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Competence;

public class CompetenceDao extends Dao {
	
	private List<Competence> competences= new ArrayList<>();
	private Competence competence;
	
	public CompetenceDao(Connection uConnection) {
		super(uConnection);
	}
	
	public void selectCompetenceByMetier(long idMetier) {
		String sql= "SELECT C.Nom, C.Id ";
		sql+= "FROM Metier_Competence AS MC, Competences AS C ";
		sql+= "WHERE MC.IdMetier= ? AND C.Id= MC.IdCompetence;";
		
		try {
			PreparedStatement ps= initPs(sql, false, idMetier);
			ResultSet r= ps.executeQuery();
			list(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void add(long idMetier, String competence) {
		String sql= "INSERT INTO Competences (Nom) VALUES (?)";
		PreparedStatement ps= initPs(sql, true, competence);
		try {
			ps.executeUpdate();
			ResultSet rs= ps.getGeneratedKeys();
			if(rs.next()) {
				long idCompetence=rs.getInt(1);
				addMetierCompetence(idMetier, idCompetence);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addMetierCompetence(long idMetier, long idCompetence) {
		String sql= "INSERT INTO Metier_Competence (IdMetier, IdCompetence)	VALUES (?, ?)";
		PreparedStatement ps= initPs(sql, false, idMetier, idCompetence);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void effacer(long idCompetence, long idMetier) {
		String sql= "DELETE FROM Metier_Competence WHERE IdCompetence= ? AND IdMetier= ?";
		PreparedStatement ps= initPs(sql, false, idCompetence, idMetier);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void list(ResultSet r) {
		try {
			while(r.next()) {
				Competence newC= new Competence();
				newC.setId(r.getLong("Id"));
				newC.setNom(r.getString("Nom"));
				
				competence= newC;
				competences.add(newC);
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
}
