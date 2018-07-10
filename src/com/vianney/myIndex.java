package com.vianney;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class myIndex {

	public static void main(String[] args) throws Exception {
		
		Properties props = new Properties();
		try (FileInputStream fis= new FileInputStream("conf.properties")) {
			props.load(fis);
		}
		
		Class.forName(props.getProperty("jdbc.driver.class"));
		
		String url = props.getProperty("jdbc.url");
		String utilisateur =  props.getProperty("jdbc.login");
		String motDePasse =  props.getProperty("jdbc.password");
		
		try (Connection connection= DriverManager.getConnection(url, utilisateur, motDePasse)) {
			String sql= "SELECT * FROM Utilisateurs";
			
			 try (Statement statement = connection.createStatement()) {
		    	try (ResultSet rs= statement.executeQuery(sql)) {
		    		while (rs.next()) {
		    			String nom= rs.getString("Nom");
		    			String prenom= rs.getString("prenom");
		    			
		    			System.out.println("Nom: "+ nom +" Prenom:"+ prenom);
		    		}
		    	}
		    }
		}
	}

}
