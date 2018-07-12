package com.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class LogConnection {
	private String chemin= "C:\\Users\\59013-17-09\\workspace\\ProjetEportfolio\\WebContent\\WEB-INF\\log\\";
	private String nomFichier= chemin+dateJour();
	private File fichier;
	private FileWriter writer;
	private static PrintWriter out;

	public LogConnection() {
		creationFichier();
	}
	
	public void ecrireLigne(String ligne) {
		out.println(ligne);
		out.flush();
	}
	
	public static void fermer() {
		out.close();
	}
	
	private String dateJour() {
		LocalDate today = LocalDate.now();
		String ligne= today.getDayOfMonth() +"-"+ today.getMonthValue() +"-"+today.getYear()+".log";
		
		 return ligne;
	}
	
	private void creationFichier() {
		fichier= new File(nomFichier);
		
		if(!fichier.isFile()) {
			try {
				fichier.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			writer= new FileWriter(fichier, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out = new PrintWriter(new BufferedWriter(writer));
	}
}
