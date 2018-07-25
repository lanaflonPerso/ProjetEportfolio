package com.vianney.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Certification;
import com.vianney.beans.Competence;

public class CertificationDao extends Dao {

	private List<Certification> certifications= new ArrayList<>();
	private Certification certification;
	
	public CertificationDao(Connection uConnection) {
		super(uConnection);
	}
}
