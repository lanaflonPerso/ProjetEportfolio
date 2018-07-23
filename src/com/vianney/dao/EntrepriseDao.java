package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Entreprise;

public class EntrepriseDao extends Dao {
	
	private List<Entreprise> entreprises= new ArrayList<>();
	private Entreprise entreprise;

	public EntrepriseDao(Connection uConnection) {
		super(uConnection);
	}
	
	public void selectById(long id) {
		String sql= "SELECT Id AS IdEntreprise, Nom AS NomEntreprise, Adresse, Ville, CodePostal FROM Entreprises WHERE Id= ?";
		
		try {
			PreparedStatement ps= initPs(sql, false, id);
			ResultSet r= ps.executeQuery();
			unique(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Entreprise selectByMetier(long idMetier) {

		String sql= "SELECT E.Id AS IdEntreprise, E.Nom AS NomEntreprise, E.Adresse, ";
		sql+= "E.Ville, E.CodePostal ";
		sql+= "FROM Entreprises AS E, Metier_Entreprise AS ME, Metiers AS M ";
		sql+= "WHERE ME.IdMetier= ? AND ME.IdEntreprise= E.Id";
		try {
			PreparedStatement ps= initPs(sql, false, idMetier);
			ResultSet r= ps.executeQuery();
			unique(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entreprise;
	}
	
	public void selectByStagaire(long idStagiaire) {
		String sql= "SELECT E.Id AS IdEntreprise, E.Nom AS NomEntreprise, E.Adresse, ";
		sql+= "E.Ville, E.CodePostal, M.Id AS IdMetier, M.Function, ";
		sql+= "FROM Entreprises AS E, Metier_Entreprise AS ME, Metiers AS M, Stagiaire_Metier AS SM ";
		sql+= "WHERE SM.IdStagiaire= ? AND ME.IdMetier= SM.IdMetier AND ME.IdEntreprise= E.Id AND SM.IdMetier= M.Id";
		try {
			PreparedStatement ps= initPs(sql, false, idStagiaire);
			ResultSet r= ps.executeQuery();
			unique(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean SelectByNom(String nom) {
		String sql= "SELECT Id FROM Entreprises WHERE Nom= ?";
		try {
			PreparedStatement ps= initPs(sql, false, nom);
			ResultSet r= ps.executeQuery();
			if (r.next()) {
				r.first();
				unique(r);
				return true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean InsertEntMetier(long idEntreprise, long idMetier) {
		String sql= "INSERT INTO  Metier_Entreprise";
		sql+= " (IdMetier, IdEntreprise)";
		sql+= " VALUES (?, ?);";
		PreparedStatement ps= initPs(sql, false, idMetier, idEntreprise);
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean InsertEntreprise(Entreprise ent) {
		String sql= "INSERT INTO  Entreprises";
		sql+= " (Nom, Adresse, Ville, CodePostal)";
		sql+= " VALUES (?, ?, ?, ?);";
		PreparedStatement ps= initPs(sql, false, ent.getNom(), ent.getAdresse(), ent.getVille(), ent.getCodePostal());
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private void unique(ResultSet r) {
		try {
			while(r.next()) {
				entreprise= new Entreprise();
				entreprise.setId(r.getLong("IdEntreprise"));
				entreprise.setNom(r.getString("NomEntreprise"));
				entreprise.setAdresse(r.getString("Adresse"));
				entreprise.setVille(r.getString("Ville"));
				entreprise.setCodePostal(r.getInt("CodePostal"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 	public List<Entreprise> getEntreprises() {
		return entreprises;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
}
