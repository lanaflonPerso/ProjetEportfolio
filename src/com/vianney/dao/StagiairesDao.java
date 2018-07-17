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
	
	private Connection connection;
	List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
	List<Entreprise> rowsEntreprise = new ArrayList<Entreprise>();

	
	public StagiairesDao(Connection connection) {
		this.connection = connection;
	}
		
	public Stagiaire SelectById(long id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql= "SELECT * FROM Stagiaires WHERE id= ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong( 1, id );
			resultSet= preparedStatement.executeQuery();
			createList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stagiaires.get(0);
	}
	
	public List<Stagiaire> searchAll() {
		ResultSet resultSet = null;
		
		String sql= "SELECT * FROM Stagiaires";
		try {
			Statement statement= connection.createStatement();
			resultSet= statement.executeQuery(sql);
			createList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stagiaires;
	}
	
	public long newStagiaire(Stagiaire stagiaire) {
		
		long id = 0;
				
		String sql= "INSERT INTO  Stagiaires";
		sql+= 			" (Nom, Prenom, Adresse, DateNaissance, IsStagiaire, IsAdministrateur, Email)";
		sql+= " VALUES    (?,   ?,      '',       ?,             0,           0,                ?);";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			
			ps.setString(1, stagiaire.getNom());
			ps.setString(2, stagiaire.getPrenom());
			String d= formatDate (stagiaire.getDateNaissance());
			ps.setString(3, d);
			ps.setString(4, stagiaire.getEmail());
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next()) {
				id=rs.getInt(1);
				return id;
			} 
			
		} catch (SQLException e) {
			System.out.println("Problème de base de donnée");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return id;
	}
	
	private void createList(ResultSet r) {
		try {
			while (r.next()) {
			    Stagiaire stagiaire = new Stagiaire();
			    stagiaire.setId(r.getLong("Id"));
			    stagiaire.setNom(r.getString("Nom"));
			    stagiaire.setPrenom(r.getString("Prenom"));
			    stagiaire.setEmail(r.getString("Email"));
			    stagiaire.setAdresse(r.getString("Adresse"));
			    
			    String[] part= r.getString("DateNaissance").split("-");
			    LocalDate localDate = LocalDate.of(Integer.parseInt(part[0]), Integer.parseInt(part[1]), Integer.parseInt(part[2]));
			    stagiaire.setDateNaissance(localDate);
			    
			    stagiaires.add(stagiaire); 
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