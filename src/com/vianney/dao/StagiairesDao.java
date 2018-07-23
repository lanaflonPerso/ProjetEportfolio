package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Stagiaire;

public class StagiairesDao extends Dao {
	
	private Connection connection;
	private List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
	private Stagiaire stagiaire = new Stagiaire();
	
	public StagiairesDao(Connection uConnection) {
		super(uConnection);
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
	
	public boolean SelectByMail(String email) {
		String sql= "SELECT * FROM Stagiaires WHERE email= ?";
		PreparedStatement ps= initPs(sql, email);
		ResultSet r;
		try {
			r = ps.executeQuery();
			if (r.next()) {
				r.beforeFirst();
				createList(r);
				return true;
			}
			createList(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return false;
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
	
	public boolean SelectByEmailMdp(String mdp, String email) {	
		String sql= "SELECT * FROM Stagiaires WHERE email= ? AND MotDePasse= ?";
		try {
			PreparedStatement ps= initPs(sql, email, mdp);
			ResultSet r= ps.executeQuery();
			if(testR(r)) {
				createList(r);
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean updateStagiaire(Stagiaire stagiaire) {
		String sql= "UPDATE Stagiaires ";
		sql+= "SET Nom= ?, Prenom= ?, Email= ?, Adresse= ?, DateNaissance= ? ";
		sql+= "WHERE Id= ?";
		
		String ddn= formatDate (stagiaire.getDateNaissance());
		PreparedStatement ps= initPs(sql, stagiaire.getNom(), stagiaire.getPrenom(), stagiaire.getEmail(), stagiaire.getAdresse(), ddn, stagiaire.getId());
		try {
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void changeMdp(long id, String mdp) {
		String sql= "UPDATE Stagiaires SET MotDePasse = ? WHERE id= ?";
		PreparedStatement ps= initPs(sql, mdp, id);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createList(ResultSet r) {
		try {
			while (r.next()) {
			    stagiaire.setId(r.getLong("Id"));
			    stagiaire.setNom(r.getString("Nom"));
			    stagiaire.setPrenom(r.getString("Prenom"));
			    stagiaire.setEmail(r.getString("Email"));
			    stagiaire.setAdresse(r.getString("Adresse"));
			    stagiaire.setDateNaissance(r.getString("DateNaissance"));
			    
			    stagiaires.add(stagiaire); 
			}    			
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Stagiaire getStagiaire() {
		return stagiaire;
	}
	
	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}
	

}