package com.vianney.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {
	
	private String url;
	private String user;
	private String password;
	private Connection connection;
	
	
	public MyConnection() {
		lireProperties();
		try {
			connection= DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Problème de base de donnée");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void lireProperties() {
		
		Properties props = new Properties();
		try (FileInputStream fis= new FileInputStream("C:\\Users\\59013-17-09\\workspace\\ProjetEportfolio\\dao.properties")) {
			props.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier 'dao.properties' ne peut etre lu");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			Class.forName(props.getProperty("jdbc.driver.class"));
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		url = props.getProperty("jdbc.url");
		user =  props.getProperty("jdbc.login");
		password =  props.getProperty("jdbc.password");
	}
	
	public Connection getConnection() {
		return connection;
	}
}
