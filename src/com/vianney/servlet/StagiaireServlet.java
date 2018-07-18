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
	private Stagiaire stagiaire;

    public StagiaireServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String[] pathInfo= request.getPathInfo().split("/");
		
		try {
			StagiairesDao conStagiaire= new StagiairesDao((Connection) request.getAttribute("connection"));
			stagiaire= conStagiaire.SelectByMail(pathInfo[1]);
		} catch (Exception e) {
			stagiaire= new Stagiaire();
		}
		
		request.setAttribute("page", "test");
		request.setAttribute("stagiaire", stagiaire);
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
		
//		try {
//			long id= Integer.parseInt(pathInfo[1]);
//			try {
//				StagiairesDao conStagiaire= new StagiairesDao((Connection) request.getAttribute("connection"));
//				stagiaire= conStagiaire.CarriereStagaire(id);
//				
//				
//				request.setAttribute("stagiaire", stagiaire);	
//				request.setAttribute("page", "stagiaire");
//				request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
//			} catch (Exception e) {
//				System.out.println("n'existe pas");
//			}
//		} catch (Exception e) {
//			System.out.println("integer");
//		}
	}
}
