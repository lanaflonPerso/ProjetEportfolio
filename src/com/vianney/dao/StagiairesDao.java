package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Entreprise;
import com.vianney.beans.Stagiaire;

public class StagiairesDao {
	
	private final String NOM_TABLE= "Stagiaires";
	private Connection connection;
	List<Stagiaire> rows = new ArrayList<Stagiaire>();
	List<Entreprise> rowsEntreprise = new ArrayList<Entreprise>();
	private Stagiaire stagaire;

	
	public StagiairesDao(Connection connection) {
		this.connection = connection;
	}
	
	public Stagiaire SelectByMail(String email) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql= "SELECT * FROM "+  NOM_TABLE +" WHERE Email= ?";
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
		
		String sql= "SELECT * FROM "+  NOM_TABLE +" WHERE id= ?";
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
		
		String sql= "SELECT * FROM "+ NOM_TABLE;
		
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
	
	public void newStagiaire(Stagiaire stagiaire) {
		String sql= "INSERT INTO Stagiaires ";
		sql+= 			"(Nom, Prenom, Adresse, DateNaissance, IsStagiaire, IsAdministrateur, Email)";
		sql+= "VALUES    (?,   ?,      '',       ?,             0,           0,                ?);";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, stagiaire.getNom());
			ps.setString(2, stagiaire.getPrenom());
			String d= formatDate (stagiaire.getDateNaissance());
			ps.setString(3, d);
			ps.setString(4, stagiaire.getEmail());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Problème de base de donnée");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
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
	private String formatDate (LocalDate date) { 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = date.format(formatter);
		
		return formattedDate;
	}
}