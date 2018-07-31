package com.vianney.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.vianney.dao.MyConnection;

public class connectJDBC implements Filter {

	public Connection connection = null;
	
    public connectJDBC() {
    }

	public void destroy() {
		try {
			connection.close();
			System.out.println("\t\tArrêt de la connexion\n\n");
		} catch (SQLException ex) {
			System.out.println("Erreur sur l'arrêt de la connexion à la base de données");
}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request.getAttribute("connection") instanceof Connection == false) {
			MyConnection conn= new MyConnection();
			connection= conn.getConnection();
			request.setAttribute("connection", connection);
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
