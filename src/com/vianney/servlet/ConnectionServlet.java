package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.form.ControleConnection;

public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ConnectionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("page", "connection");
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection= (Connection) request.getAttribute("connection");
		String mdp= (String) request.getParameter("mdp");
		String email= (String) request.getParameter("email");
		

		ControleConnection cc= new ControleConnection(connection, mdp, email);
		if(cc.isOk()) {
			request.setAttribute("page", "index");
		} else {
			request.setAttribute("var", cc);			
			request.setAttribute("page", "connection");
		}
		
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);		
	}

}
