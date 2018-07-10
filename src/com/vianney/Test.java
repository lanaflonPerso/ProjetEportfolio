package com.vianney;


public class Test {

	public static void main(String[] args) {
		Stagiaire jean= new Stagiaire("jean");
		jean.setDateNaissance("08/07/1979");
		
		System.out.printf("%s née le %s\n", jean.getNom(), jean.getDateNaissance());
		
		jean.addMetier("r2cc5", "macon");
		jean.addMetier("r2cc7", "architecte");
		
		jean.effacerMetier("architecte");
		
		System.out.println(jean.getAge());
		jean.listerMetier();
		
	}
}
