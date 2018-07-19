package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		PortfolioDAO pf= new PortfolioDAO((Connection) request.getAttribute("connection"), 2);
		Stagiaire stagiaire= pf.getStagiaire();
		
		System.out.println("Nom: "+stagiaire.getNom());
		System.out.println("Prenom: "+stagiaire.getPrenom());
		System.out.println("Email: "+stagiaire.getEmail());
		
		for (Entreprise  entreprise: stagiaire.getEntreprises()) {
			System.out.println("Entreprise Nom: "+entreprise.getNom());
			System.out.println("Entreprise Ville: "+entreprise.getVille());
			
			for (Metier metier : entreprise.getMetier()) {
				System.out.println("Metier Desc: "+metier.getDescription());
				System.out.println("Metier Fonc: "+metier.getFonction());
			}
		}
		
		request.setAttribute("page", "index");
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}