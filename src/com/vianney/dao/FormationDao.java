package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Certification;
import com.vianney.beans.Formation;

public class FormationDao extends Dao {
	
	private List<Formation> formations= new ArrayList<>();
	private Formation formation;

	public FormationDao(Connection uConnection) {
		super(uConnection);
	}
	
	public void selectByStagiaire(long idStagiaire) {
		
		String sql= "SELECT F.Id AS IdFormation, F.IdCertification, F.IntituleFormation, C.Nom, C.Niveau, C.Id AS IdCompetence ";
		sql+= "FROM Stagiaire_Formation as SF, Formations as F LEFT OUTER JOIN Certifications AS C ON F.IdCertification = C.Id ";
		sql+= "WHERE SF.IdStagiaire= ? AND SF.IdFormation= F.Id AND SF.IdFormation = F.Id";
		
		PreparedStatement ps= initPs(sql, false, idStagiaire);
		
		ResultSet r;
		try {
			r = ps.executeQuery();
			list(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void add(String nomCertification, int niveau, String intituleFormation, long idStagiaire,Boolean withCertification) {
		long idCertification= 0;
		
		if (withCertification) {
			idCertification= addCertification(nomCertification, niveau);
		}
		
		long idFormation= addFormation(intituleFormation, idCertification);
		addStagiaireFormation(idStagiaire, idFormation);
	}
	
	public long addCertification(String nom, int niveau) {
		long idCertification= 0;
		String sql= "INSERT INTO Certifications(Nom, Niveau) VALUES (?, ?)";
		try {
			PreparedStatement ps= initPs(sql, true, nom, niveau);
			ps.executeUpdate();
			ResultSet rs;
			rs = ps.getGeneratedKeys();

			if(rs.next()) {
				return idCertification=rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return idCertification;	
	}
	
	public void addStagiaireFormation(long idStagiaire, long idFormation) {
		String sql= "INSERT INTO Stagiaire_Formation(IdStagiaire, IdFormation)	VALUES (?, ?)";
		try {
			PreparedStatement ps= initPs(sql, false, idStagiaire, idFormation);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public long addFormation(String intituleFormation, long idCertification) {
		String sql;
		PreparedStatement ps;
		
		if (idCertification == 0) {
			sql= "INSERT INTO Formations (IntituleFormation) VALUES (?)";
			ps= initPs(sql, true, intituleFormation);
		} else {
			sql= "INSERT INTO Formations (IntituleFormation, IdCertification) VALUES (?, ?)";
			ps= initPs(sql, true, intituleFormation, idCertification);
		}
		long idFormation = 0;
		
		try {
			ps.executeUpdate();
			ResultSet rs;
			rs = ps.getGeneratedKeys();

			if(rs.next()) {
				return idFormation=rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idFormation;
	}
	
	public void list(ResultSet r) {
		try {
			while(r.next()) {
				Formation newF= new Formation();
				newF.setId(r.getLong("IdFormation"));				
				newF.setIntituleFormation(r.getString("intituleFormation"));
				
				Certification certification= new Certification();
				if ( r.getLong("IdCertification") != 0) {
					certification.setId(r.getLong("IdCertification"));
					certification.setNom(r.getString("Nom"));
					certification.setNiveau(r.getInt("Niveau"));
				}
				newF.setCertification(certification);
				formation= newF;
				formations.add(newF);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public Formation getFormation() {
		return formation;
	}
}
