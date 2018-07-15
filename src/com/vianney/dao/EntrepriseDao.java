package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Entreprise;

public class EntrepriseDao {
	private Connection connection;
	List<Entreprise> rows = new ArrayList<Entreprise>();
	private Entreprise entreprise;
	
	public EntrepriseDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Entreprise> SelectByStudent(int id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql= "SELECT * FROM Entreprises WHERE IdUtilisateur = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt( 1, id );
			resultSet= preparedStatement.executeQuery();
			createList(resultSet, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rows;	
	}
	
	private void createList(ResultSet resultSet, Boolean bool) {
		try {
			while (resultSet.next()) {
			    Entreprise newEntreprise = new Entreprise();
			    newEntreprise.setNom(resultSet.getString("Id"));
			    newEntreprise.setAdresse(resultSet.getString("Adresse"));
			    newEntreprise.setVille(resultSet.getString("Ville"));
			    newEntreprise.setNom(resultSet.getString("Nom"));
			    
			    String[] partDE= resultSet.getString("DateEntree").split("-");
			    LocalDate localDateDE = LocalDate.of(Integer.parseInt(partDE[0]), Integer.parseInt(partDE[1]), Integer.parseInt(partDE[2]));
			    newEntreprise.setDateEntree(localDateDE);
			    
			    String[] partDS= resultSet.getString("DateEntree").split("-");
			    LocalDate localDateDS = LocalDate.of(Integer.parseInt(partDS[0]), Integer.parseInt(partDS[1]), Integer.parseInt(partDS[2]));
			    newEntreprise.setDateSortie(localDateDS);
			    
			    if (bool) {
			    	rows.add(newEntreprise);
			    } else {
			    	entreprise = newEntreprise;
			    }
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
}
