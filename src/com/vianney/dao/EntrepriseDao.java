package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Entreprise;
import com.vianney.beans.Metier;

public class EntrepriseDao {
	
	private final String NOM_TABLE= "Entreprises";
	private Connection connection;
	List<Entreprise> rows = new ArrayList<Entreprise>();
	List<Metier> metiers = new ArrayList<Metier>();
	private Entreprise entreprise;
	private Metier metier;
	
	public EntrepriseDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Entreprise> SelectByStudent(int id) {
		
		SelectMetier(id);
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql= "SELECT * FROM "+ NOM_TABLE +" WHERE IdStagiaire = ?";
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
	
	public void SelectMetier(int id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql= "SELECT	SM.IdMetier, SM.DateEntree, SM.DateSortie, SM.Description,";
		sql+= "S.Nom, S.Prenom, S.Email, S.Adresse, S.DateNaissance, ";
		sql+= "M.Fonction";
		sql+= "FROM 	Stagiaire_Metier AS SM, ";
		sql+= "Stagiaires AS S, ";
		sql+= "Metiers AS M ";
		sql+= "WHERE S.Id= 2 AND SM.IdStagiaire= 2 AND M.Id = SM.IdMetier;";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt( 1, id );
			resultSet= preparedStatement.executeQuery();
			
			while (resultSet.next()) {
			
				metier.setId(resultSet.getInt("Id"));
				String[] partDE= resultSet.getString("DateEntree").split("-");
			    LocalDate localDateDE = LocalDate.of(Integer.parseInt(partDE[0]), Integer.parseInt(partDE[1]), Integer.parseInt(partDE[2]));
			    metier.setDateEntree(localDateDE);
			    
			    String[] partDS= resultSet.getString("DateEntree").split("-");
			    LocalDate localDateDS = LocalDate.of(Integer.parseInt(partDS[0]), Integer.parseInt(partDS[1]), Integer.parseInt(partDS[2]));
			    metier.setDateSortie(localDateDS);
			    
			    metier.setDescription(resultSet.getString("Description"));
			    metier.setFonction(resultSet.getString("Fonction"));
			    
			    metiers.add(metier);
			}
			  		
		} catch (SQLException e) {
			System.out.println("Erreur dans Entreprise DAO");
			e.printStackTrace();
		}	
	}
	
	private void createList(ResultSet resultSet, Boolean bool) {
		try {
			while (resultSet.next()) {
			    Entreprise newEntreprise = new Entreprise();
			    newEntreprise.setNom(resultSet.getString("Id"));
			    newEntreprise.setAdresse(resultSet.getString("Adresse"));
			    newEntreprise.setVille(resultSet.getString("Ville"));
			    newEntreprise.setNom(resultSet.getString("Nom"));
			 
			    
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