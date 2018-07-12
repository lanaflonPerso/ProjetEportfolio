package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Stagiaire;


public class StagiaireDao {
	
	private Connection connection;
	List<Stagiaire> rows = new ArrayList<Stagiaire>();

	
	public StagiaireDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Stagiaire> searchByMail(String email) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql= "SELECT * FROM Utilisateurs WHERE Email= ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			preparedStatement.setString( 1, email );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			resultSet= preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		createList(resultSet);
		return rows;	
	}
	
	public List<Stagiaire> searchAll() {
		
		String sql= "SELECT * FROM Utilisateurs";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet= statement.executeQuery(sql);
			createList(resultSet);
		} catch (SQLException e) {
			System.out.println("Problème de base de donnée");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return rows;	
	}
	
	private void createList(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
			    Stagiaire stagiaire = new Stagiaire();
			    stagiaire.setNom(resultSet.getString("Nom"));
			    stagiaire.setPrenom(resultSet.getString("Prenom"));
			    stagiaire.setEmail(resultSet.getString("Email"));
			    stagiaire.setAdresse(resultSet.getString("Adresse"));
			    
			    String[] part= resultSet.getString("DateNaissance").split("-");
			    LocalDate localDate = LocalDate.of(Integer.parseInt(part[0]), Integer.parseInt(part[1]), Integer.parseInt(part[2]));
			    stagiaire.setDateNaissance(localDate);
			    
			    rows.add(stagiaire);
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
}