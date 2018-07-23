package com.vianney.form;

import java.sql.Connection;

public class Ctrl {
	
	protected boolean ok= true;
	protected Connection connection;
	
	public Ctrl(Connection uConnection) {
		connection= uConnection;
	}
	
 	protected String classe(boolean b) {
		if (b) {
			return "is-valid";
		} else {
			return "is-invalid";
		}
	}
 	
	public boolean isOk() {
		return ok;
	}
}
