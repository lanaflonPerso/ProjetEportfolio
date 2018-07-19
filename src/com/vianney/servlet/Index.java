package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Competence;
import com.vianney.beans.Entreprise;
import com.vianney.beans.Metier;
import com.vianney.beans.Stagiaire;
import com.vianney.dao.PortfolioDAO;

public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id= Long.parseLong(request.getParameter("stagiaire"));
		
		PortfolioDAO pf= new PortfolioDAO((Connection) request.getAttribute("connection"), id);
		Stagiaire stagiaire= pf.getStagiaire();
		
		System.out.println("Nom: "+stagiaire.getNom());
		System.out.println("Prenom: "+stagiaire.getPrenom());
		System.out.println("Email: "+stagiaire.getEmail());
	
		
		for (Metier  metier: stagiaire.getMetiers()) {
			for (Entreprise entreprise: metier.getEntreprises()) {
				System.out.println("Entreprise Nom: "+entreprise.getNom());
				System.out.println("Entreprise Ville: "+entreprise.getVille());
				System.out.println(metier.getId()+" Metier Desc: "+metier.getDescription());
				System.out.println("Metier Fonc: "+metier.getFonction());
				
				for(Competence competence : metier.getCompetences()) {
					System.out.println("\tCompetence Fonc: "+competence.getNom());
				}
			}
		}
		request.setAttribute("stagiaire", stagiaire);
		request.setAttribute("page", "stagiaire");
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}