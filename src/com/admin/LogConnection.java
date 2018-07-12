package com.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javafx.util.converter.LocalDateStringConverter;

public class LogConnection {
	private String nomFichier= "C:\\Users\\59013-17-09\\workspace\\ProjetEportfolio\\WebContent\\WEB-INF\\log\\inscription.log";
	private File fichier;
	private FileWriter writer;
	private static PrintWriter out;

	public LogConnection() throws IOException {
		
		dateJour();
		fichier= new File(nomFichier);
		
		if(!fichier.isFile()) {
			try {
				fichier.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		writer= new FileWriter(fichier, true);
		out = new PrintWriter(new BufferedWriter(writer));
	}
	
	public void ecrireLigne(String ligne) {
		out.println(ligne);
		out.flush();
	}
	
	public static void fermer() {
		out.close();
	}
	
	private void dateJour() {
		LocalDate today = LocalDate.now();
		String ligne= today.getDayOfMonth() +"/"+ today.getMonthValue() +"/"+today.getYear();
		
		 System.out.println(ligne);
	}
}
