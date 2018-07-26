package com.vianney.form;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ctrl {
	
	protected boolean ok= true;
	protected Connection connection;
	protected LocalDate now;
	
	public Ctrl(Connection uConnection) {
		connection= uConnection;
		now = LocalDate.now();
	}
	
 	protected String classe(boolean b) {
		if (b) {
			return "is-valid";
		} else {
			return "is-invalid";
		}
	}
 	
 	protected boolean ctrlDate(String date) {
 		Pattern regexDate = Pattern.compile("^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$", Pattern.CASE_INSENSITIVE);
		Matcher m = regexDate.matcher(date);
		if (m.find()) {
			return true;
		}
		return false;
 	}
 	
 	protected int yearNow() {
 		now= LocalDate.now();
 		return now.getYear();
 	}
 	
	public boolean isOk() {
		return ok;
	}
}
