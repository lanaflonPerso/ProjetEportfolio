package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Competence;
import com.vianney.beans.Formation;

public class FormationDao extends Dao {
	
	private List<Formation> formations= new ArrayList<>();
	private Formation formation;

	public FormationDao(Connection uConnection) {
		super(uConnection);
	}
	
	public void selectByStagiaire(long idStagiaire) {
		String sql= "SELECT F.Id, F.IdCertification, F.IntituleFormation ";
		sql+= "FROM formations as F, stagiaire_formation as SF ";
		sql+= "WHERE SF.Id= ? AND SF.IdFormation= F.Id";
		
		PreparedStatement ps= initPs(sql, false, idStagiaire);
		
		ResultSet r;
		try {
			r = ps.executeQuery();
			list(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void list(ResultSet r) {
		try {
			while(r.next()) {
				Formation newF= new Formation();
				newF.setId(r.getLong("Id"));				
				newF.setIntituleFormation(r.getString("intituleFormation"));
				
				System.out.println("Intituler: "+newF.getIntituleFormation());
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
