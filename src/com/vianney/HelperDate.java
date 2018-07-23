package com.vianney;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperDate {

	public static String formatDateFR(LocalDate date) { 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
		String formattedDate = date.format(formatter);
		
		return formattedDate;
	}
	
}
