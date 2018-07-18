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
	List<Entreprise> entreprises = new ArrayList<Entreprise>();
	
	public EntrepriseDao(Connection connection) {
		this.connection = connection;
	}
	
	public void SelectByStudent(int id) { //id = id_metier
			
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql= "SELECT * ";
		sql+= "FROM Entreprises AS E, Metier_Entreprise AS ME";
		sql+= "WHERE ME.IdMetier= ? AND E.ID= ME.IdEntreprise";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt( 1, id );
			resultSet= preparedStatement.executeQuery();
			createList(resultSet, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createList(ResultSet resultSet, Boolean bool) {
		try {
			while (resultSet.next()) {
			    Entreprise entreprise = new Entreprise();
			    entreprise.setNom(resultSet.getString("Id"));
			    entreprise.setAdresse(resultSet.getString("Adresse"));
			    entreprise.setVille(resultSet.getString("Ville"));
			    entreprise.setNom(resultSet.getString("Nom"));
			    entreprise.setNom(resultSet.getString("CodePostal"));
			    
			    entreprises.add(entreprise);
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Entreprise> getEntreprises() {
		return entreprises;
	}
}