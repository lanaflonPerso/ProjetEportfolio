package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.PortfolioDAO;

public class StagiaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String page2= "Stagiaire";
	public final String page= "/WEB-INF/vue/Stagiaire.jsp";

    public StagiaireServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathInfo= request.getPathInfo().split("/");
		try {
			long id= Integer.parseInt(pathInfo[1]);
			try {
				PortfolioDAO pf= new PortfolioDAO((Connection) request.getAttribute("connection"), id);
				Stagiaire stagiaire= pf.getStagiaire();

				request.setAttribute("stagiaire", stagiaire);	
			} catch (Exception e) {
				System.out.println("n'existe pas");
			}
		} catch (Exception e) {
			
		}
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("Index.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String[] pathInfo= request.getPathInfo().split("/");
//		request.getRequestDispatcher("Index.jsp").forward(request, response);
	}
}
