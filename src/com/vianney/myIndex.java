package com.vianney;

import java.util.List;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.MyConnection;
import com.vianney.dao.StagiaireDao;

public class myIndex {

	public static void main(String[] args) throws Exception {
		StagiaireDao newConnect= new StagiaireDao(new MyConnection().getConnection());
		
		List<Stagiaire> r= newConnect.searchAll();
		
		for (Stagiaire stagiaire : r) {
			System.out.printf("nom= %s, prenom= %s, emal= %s\n", stagiaire.getNom(), stagiaire.getPrenom(), stagiaire.getEmail());
		}
	}
}
