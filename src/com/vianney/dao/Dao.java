package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Dao {
	
	protected Connection connection;
	protected  boolean ok= true;

	public Dao(Connection uConnection) {
		this.connection = uConnection;
	}
	
	protected String formatDate(LocalDate date) { 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = date.format(formatter);
		
		return formattedDate;
	}
		
	protected PreparedStatement initPs(String sql, Object... objects) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			for ( int i = 0; i < objects.length; i++ ) {
				ps.setObject( i + 1, objects[i] );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	public boolean isOk() {
		return ok;
	}
	
	public boolean testR(ResultSet r) throws SQLException {
		if(r.next()) {
			r.beforeFirst();
			return true;
		}
		return false;
	}
}
