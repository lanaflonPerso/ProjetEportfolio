package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Stagiaire;

public class StagiairesDao extends Dao {
	
	private List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
	private Stagiaire stagiaire = new Stagiaire();
	
	public StagiairesDao(Connection uConnection) {
		super(uConnection);
	}
	
	public boolean SelectById(long id) {

		String sql= "SELECT * FROM Stagiaires WHERE id= ?";
		PreparedStatement ps= initPs(sql, false, id);
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
		PreparedStatement ps= initPs(sql, false, email);
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
			PreparedStatement ps= initPs(sql, false);
			ResultSet result= ps.executeQuery();
			createList(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean selectStagiaireByIdEntreprise(long idEntreprise) {
		String sql= "SELECT S.Id, S.Nom, S.Prenom, S.Email, S.Adresse, S.DateNaissance, S.IsAdministrateur ";
		sql+= "FROM Stagiaires AS S,  Stagiaire_Metier AS SM, Metier_Entreprise AS ME ";
		sql+= "WHERE ME.IdEntreprise= ? AND ME.IdMetier= SM.IdMetier AND SM.IdStagiaire= S.Id";
		PreparedStatement ps= initPs(sql, false, idEntreprise);
		try {
			ResultSet result= ps.executeQuery();
			createList(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public long newStagiaire(Stagiaire stagiaire) {
		
		long id = 0;
				
		String sql= "INSERT INTO  Stagiaires";
		sql+= 			" (Nom, Prenom, DateNaissance, IsStagiaire, IsAdministrateur, Email, MotDePasse)";
		sql+= " VALUES    (?,   ?,      ?,             0,           0,                ?, SHA1('azerty'));";
		
		try {
			
			String d= formatDate (stagiaire.getDateNaissance());
			PreparedStatement ps= initPs(sql, true, stagiaire.getNom(), stagiaire.getPrenom(), d, stagiaire.getEmail()); 
			ps.executeUpdate();
			ResultSet rs= ps.getGeneratedKeys();
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
		String sql= "SELECT * FROM Stagiaires WHERE email= ? AND MotDePasse= SHA1(?)";
		try {
			PreparedStatement ps= initPs(sql, false, email, mdp);
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
		PreparedStatement ps= initPs(sql, false, stagiaire.getNom(), stagiaire.getPrenom(), stagiaire.getEmail(), stagiaire.getAdresse(), ddn, stagiaire.getId());
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void changeMdp(long id, String mdp) {
		String sql= "UPDATE Stagiaires SET MotDePasse = SHA1(?) WHERE id= ?";
		PreparedStatement ps= initPs(sql, false, mdp, id);
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
				LocalDate ddn= ddnLocalDate(r.getString("DateNaissance"));
				newS.setDateNaissance(ddn);
				
				boolean admin= false; 
				if (r.getInt("IsAdministrateur") == 1) {
					admin= true;
				}
				
				newS.setAdmin(admin);
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