package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Metier;

public class EntreprisesDao {
	
	private Connection connection;
	private List<Metier> metiers= new ArrayList<Metier>();

	public EntreprisesDao(Connection connection) {
		this.connection = connection;
	}
	public void SelectByStagiaire(long idMetier) {

		
		String sql= "SELECT * FROM Entreprises AS E, Metier_Entreprise AS ME WHERE ME.IdMetier= ? AND ME.IdEntreprise= E.Id";
		try {
			ResultSet r;
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong( 1, id );
			r= preparedStatement.executeQuery();
			createList(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
