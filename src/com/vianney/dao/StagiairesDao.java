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
	
	public boolean SelectById(long id) {

		String sql= "SELECT * FROM Stagiaires WHERE id= ?";
		PreparedStatement ps= initPs(sql, id);
		ResultSet result;
		try {
			result = ps.executeQuery();
			if (testR(result)) {
				createList(result);
				return true;
			}
			createList(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
	
	public void searchAll() {
		String sql= "SELECT * FROM Stagiaires";
		try {
			PreparedStatement ps= initPs(sql);
			ResultSet result= ps.executeQuery();
			createList(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				Stagiaire newS= new Stagiaire();
				newS.setId(r.getLong("Id"));
				newS.setNom(r.getString("Nom"));
				newS.setPrenom(r.getString("Prenom"));
				newS.setEmail(r.getString("Email"));
				newS.setAdresse(r.getString("Adresse"));
				newS.setDateNaissance(r.getString("DateNaissance"));
			    stagiaire= newS;
			    stagiaires.add(newS); 
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