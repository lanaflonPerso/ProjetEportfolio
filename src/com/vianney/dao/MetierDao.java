package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Metier;

public class MetierDao extends Dao {
	
	private List<Metier> metiers= new ArrayList<Metier>();
	private Metier metier= new Metier();

	public MetierDao(Connection uConnection) {
		super(uConnection);
	}
	
	public boolean SelectByStagiaire(long id) {

		String sql= "SELECT M.Id AS IdMetier, SM.DateEntree, SM.DateSortie, SM.Description, M.Fonction ";
		sql+= "FROM Stagiaire_Metier AS SM, Metiers AS M ";
		sql+= "WHERE SM.IdStagiaire= ? AND M.Id= SM.IdMetier";
			
		PreparedStatement ps= initPs(sql, false, id);
		ResultSet result;
		
		try {
			result= ps.executeQuery();
			if (testR(result)) {
				createList(result);
				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}
	

	public boolean ajouter(Metier metier, long idStagiaire) {
				
		String sql = "INSERT INTO  Metiers (Fonction) VALUES (?);";
		String sql2= "INSERT INTO Stagiaire_Metier ";
		sql2+= "(DateEntree, DateSortie, Description, IdStagiaire, IdMetier) ";
		sql2+= "VALUES (?, ?, ?, ?, ?) ";
		
		PreparedStatement ps= initPs(sql, true, metier.getFonction());
		try {
			ps.executeUpdate();
			ResultSet idMetier= ps.getGeneratedKeys();
			
			System.out.println("id metier= "+idMetier.getInt(1));
			
			ps= initPs(sql2, false, metier.getDateEntree(), metier.getDateSortie(), metier.getDescription(), idStagiaire, idMetier.getInt(1));
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	private void createList(ResultSet r) {
		try {
			while (r.next()) {
			    Metier newM = new Metier();
			    newM.setId(r.getLong("IdMetier"));
			    newM.setFonction(r.getString("Fonction"));
			    
			    String[] my= r.getString("DateEntree").split("-");
			    newM.setDateEntree(Integer.parseInt(my[2]), Integer.parseInt(my[1]), Integer.parseInt(my[0]));
			    
			    my= r.getString("DateSortie").split("-");
			    newM.setDateSortie(Integer.parseInt(my[2]), Integer.parseInt(my[1]), Integer.parseInt(my[0]));
			    newM.setDescription(r.getString("Description"));
			    
			    metier= newM;
			    metiers.add(newM);    
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Metier> getMetiers() {
		return metiers;
	}
	
	public Metier getMetier() {
		return metier;
	}
}