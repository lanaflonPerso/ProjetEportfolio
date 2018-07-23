package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Entreprise;
import com.vianney.dao.EntrepriseDao;

public class LierEntrepriseMetierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String page=			"/WEB-INF/form/LierEntrepriseMetier.jsp";
       
    public LierEntrepriseMetierServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] pathInfo= request.getPathInfo().split("/");
		try {
			long id= Integer.parseInt(pathInfo[1]);
			EntrepriseDao eDao= new EntrepriseDao((Connection) request.getAttribute("connection"));
			if(!request.getParameter("cp").equals("")) {
				eDao.likeCp(request.getParameter("cp"));
			}
			
			if(!request.getParameter("nom").equals("")) {
				eDao.likeNom(request.getParameter("nom"));
			}
			
			eDao.select();
			
			List<Entreprise> entreprises= eDao.getEntreprises();
			
			for (Entreprise entreprise : entreprises) {
				System.out.println(entreprise.getNom());
			}
			
		} catch (Exception e) {
			
		}
		
		request.setAttribute("url", "/ProjetEportfolio/metier/ajouter");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
