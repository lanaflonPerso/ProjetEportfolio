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
			
		PreparedStatement ps= initPs(sql, id);
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
	
	private void createList(ResultSet r) {
		try {
			while (r.next()) {
			    Metier newM = new Metier();
			    newM.setId(r.getLong("IdMetier"));
			    newM.setFonction(r.getString("Fonction"));
			    newM.setDateEntree(r.getString("DateEntree"));
			    newM.setDateSortie(r.getString("DateSortie"));
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