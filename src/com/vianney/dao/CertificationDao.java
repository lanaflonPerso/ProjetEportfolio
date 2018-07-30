package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vianney.beans.Certification;

public class CertificationDao extends Dao {

	private Certification certification= new Certification();
	
	public CertificationDao(Connection uConnection) {
		super(uConnection);
	}
	
	public void selectByIdCertification(long idCertification) {
		String sql= "SELECT * FROM Certifications WHERE Id= ?";
		
		PreparedStatement ps= initPs(sql, false, idCertification);
		ResultSet r;
		try {
			r = ps.executeQuery();
			list(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void list(ResultSet r) {
		try {
			if (r.next()) {
				certification.setId(r.getLong("Id"));				
				certification.setNom(r.getString("Nom"));
				certification.setNiveau(r.getInt("Niveau"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Certification getCertification() {
		return certification;
	}
}
