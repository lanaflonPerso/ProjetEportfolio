package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.PortfolioDAO;

public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String page= "/WEB-INF/vue/Home.jsp";
       
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id= Long.parseLong(request.getParameter("stagiaire"));

		PortfolioDAO pf= new PortfolioDAO((Connection) request.getAttribute("connection"), id);
		Stagiaire stagiaire= pf.getStagiaire();
			
		request.setAttribute("stagiaire", stagiaire);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}