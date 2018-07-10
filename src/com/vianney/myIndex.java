package com.vianney;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class myIndex {

	public static void main(String[] args) throws Exception {
		
		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost:3306/ProjetEportfolio?useSSL=false&serverTimezone=UTC";
		String utilisateur = "root";
		String motDePasse = "4fU1TLGv";
		Connection connexion = null;
		try {
		    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    
		    String sql= "SELECT * FROM Utilisateurs";
		    
		    try (Statement stateent = connexion.createStatement()) {
		    	try (ResultSet rs= statement.executeQuery(sql)) {
		    		while (rs.next()) {
		    			String nom= rs.getString("Nom");
		    			String prenom= rs.getString("prenom");
		    			
		    			System.out.println("Nom: "+ nom +" Prenom:"+ prenom);
		    		}
		    	}
		    }
//		    String strSqli= "INSERT INTO Utilisateurs(Nom, Prenom, Adresse, DateNaissance, IsStagiaire, IsAdministrateur) VALUES ('Eric', 'Paulet', '12 rue clarisse 59320 haubourdin', '1995-03-07', 1, 0);";
//		    statement.executeUpdate(strSqli);
		    
		} catch ( SQLException e ) {
		    System.out.println(e.getMessage());
		} finally {
		    if ( connexion != null )
		        try {
		            connexion.close();
		        } catch ( SQLException ignore ) {
		            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
		        }
		}
	}

}
