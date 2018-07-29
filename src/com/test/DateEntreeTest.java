package com.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.MyConnection;
import com.vianney.form.CtrlMetier;

public class DateEntreeTest {
	static MyConnection connection= new MyConnection();
	
	public static void main(String[] args) {
		List<String> a= new ArrayList<>();
		a.add("20/05/1979;20/05/1997;20/05/1999;null");
		a.add("20/05/1979;20-05-1997;20/05/1999;null");
		a.add("20/05/1990;20/05/1997;20/05/1999;vous ne pouvez travailler avant 16 ans");
		a.add("20/05/1979;20/05/1997;20/05/1996;les dates se chevauche");
		a.add("20/05/1979;20-05-197;20/05/1996;Format de date incorrecte exemple: 20/05/2011");
		a.add("20/05/1979;20/05/1999;20-05-200;Format de date incorrecte exemple: 20/05/2011");

		for(int i =0; i< a.size(); i++ ) {
			String[] tab= a.get(i).split(";");
			
			if (test(tab[0], tab[1], tab[2], tab[3])) {
				System.err.println("test n="+ i +" ok");
			} else {
				System.err.println("test n="+ i +" NON OK");
			}
		}

	}
	
	public static boolean test(String date, String dateE, String dateS, String message) {
		Stagiaire stagiaire= new Stagiaire();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		stagiaire.setDateNaissance(localDate);
		
		CtrlMetier ctrlM= new CtrlMetier(connection.getConnection(), stagiaire);
		
		ctrlM.ctrlDate(dateE, dateS);
		if (message.equals(ctrlM.getMsgErrDate()) || ctrlM.getMsgErrDate() == null ) {
			return true;
		} else {
			System.out.println(ctrlM.getMsgErrDate());
			return false;
		}		
	}
}
