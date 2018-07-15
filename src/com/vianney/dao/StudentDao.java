package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Entreprise;
import com.vianney.beans.Stagiaire;

public class StudentDao {
	
	private Connection connection;
	List<Stagiaire> rows = new ArrayList<Stagiaire>();
	List<Entreprise> rowsEntreprise = new ArrayList<Entreprise>();
	private Stagiaire stagaire;

	
	public StudentDao(Connection connection) {
		this.connection = connection;
	}
	
	public Stagiaire SelectByMail(String email) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql= "SELECT * FROM Utilisateurs WHERE Email= ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString( 1, email );
			resultSet= preparedStatement.executeQuery();
			createList(resultSet, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stagaire;	
	}
	
	public Stagiaire SelectById(Integer id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql= "SELECT * FROM Utilisateurs WHERE id= ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt( 1, id );
			resultSet= preparedStatement.executeQuery();
			createList(resultSet, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		EntrepriseDao listEntreprises= new EntrepriseDao(connection);
		List<Entreprise> entreprises= listEntreprises.SelectByStudent(id);
		stagaire.setEntreprises(entreprises);
		
		return stagaire;	
	}
	
	public List<Stagiaire> searchAll() {
		
		String sql= "SELECT * FROM Utilisateurs";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet= statement.executeQuery(sql);
			createList(resultSet, true);
		} catch (SQLException e) {
			System.out.println("Problème de base de donnée");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return rows;	
	}
	
	/**
	 * False renvoie un stagiaire
	 * True un une liste de stagaire
	 */
	private void createList(ResultSet resultSet, Boolean bool) {
		try {
			while (resultSet.next()) {
			    Stagiaire NewStagiaire = new Stagiaire();
			    NewStagiaire.setNom(resultSet.getString("Nom"));
			    NewStagiaire.setPrenom(resultSet.getString("Prenom"));
			    NewStagiaire.setEmail(resultSet.getString("Email"));
			    NewStagiaire.setAdresse(resultSet.getString("Adresse"));
			    
			    String[] part= resultSet.getString("DateNaissance").split("-");
			    LocalDate localDate = LocalDate.of(Integer.parseInt(part[0]), Integer.parseInt(part[1]), Integer.parseInt(part[2]));
			    NewStagiaire.setDateNaissance(localDate);
			    
			    if (bool) {
			    	rows.add(NewStagiaire);
			    } else {
			    	stagaire= NewStagiaire;
			    }
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
}