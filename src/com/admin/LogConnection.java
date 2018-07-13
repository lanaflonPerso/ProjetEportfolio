package com.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
		SimpleDateFormat format;
		Date d= new Date();
		format = new SimpleDateFormat("h:mm");
		String output = format.format(d);
		out.println(output+";"+ligne);
		out.flush();
	}
	
	public static void fermer() {
		out.close();
	}
	
	private String dateJour() {
		LocalDate dateDuJour = LocalDate.now();
		String ligne= dateDuJour.getDayOfMonth() +"-"+ dateDuJour.getMonthValue() +"-"+dateDuJour.getYear()+".log";
		
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
