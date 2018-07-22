package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.StagiairesDao;

public class StagiaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String page= "/WEB-INF/vue/Stagiaire.jsp";
	private Stagiaire stagiaire;

    public StagiaireServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] pathInfo= request.getPathInfo().split("/");
		if(!pathInfo[1].equals("modifier")) {
			System.out.println("Voir");	
			try {
				long id= Integer.parseInt(pathInfo[1]);
				try {
					StagiairesDao conStagiaire= new StagiairesDao((Connection) request.getAttribute("connection"));
					stagiaire= conStagiaire.CarriereStagaire(id);
					
					request.setAttribute("stagiaire", stagiaire);	
					request.setAttribute("page", page);
				} catch (Exception e) {
					System.out.println("n'existe pas");
				}
			} catch (Exception e) {
				
			}
			request.getRequestDispatcher("Index.jsp").forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String[] pathInfo= request.getPathInfo().split("/");
//		request.getRequestDispatcher("Index.jsp").forward(request, response);
	}
}
