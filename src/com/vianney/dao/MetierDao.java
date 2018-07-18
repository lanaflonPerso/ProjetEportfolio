package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Metier;

public class MetierDao {
	
	private Connection connection;
	private List<Metier> metiers= new ArrayList<Metier>();

	public MetierDao(Connection connection) {
		this.connection = connection;
	}
	
	public void SelectByStagiaire(long id) {

		
		String sql= "SELECT M.Id AS IdMetier, SM.DateEntree, SM.DateSortie, SM.Description, M.Fonction ";
		sql+= "FROM Stagiaire_Metier AS SM, Metiers AS M ";
		sql+= "WHERE SM.IdStagiaire= ? AND M.Id= SM.IdMetier";
		try {
			ResultSet r;
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong( 1, id );
			r= preparedStatement.executeQuery();
			createList(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createList(ResultSet r) {
		try {
			while (r.next()) {
			    Metier metier = new Metier();
			    metier.setId(r.getLong("IdMetier"));
			    metier.setFonction(r.getString("Fonction"));
			    
			    metiers.add(metier);    
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Metier> getMetiers() {
		return metiers;
	}
	
	
}
