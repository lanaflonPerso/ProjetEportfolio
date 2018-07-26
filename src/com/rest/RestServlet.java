package com.rest;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Competence;
import com.vianney.beans.Entreprise;
import com.vianney.beans.Stagiaire;
import com.vianney.dao.CompetenceDao;
import com.vianney.dao.EntrepriseDao;
import com.vianney.dao.StagiairesDao;

@WebServlet("/rest/*")
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RestServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Access-Control-Allow-Origin", "*");
			
		
		String[] pathInfo= request.getPathInfo().split("/");
		
		if(pathInfo[1].equals("entreprise")) {
			if(request.getParameter("nom") != null) {				
				response.getWriter().write(listEntreprise(request.getParameter("nom"), (Connection) request.getAttribute("connection")));
			}
		}
		
		if(pathInfo[1].equals("competence")) {
			if(request.getParameter("nom") != null) {				
				response.getWriter().write(listCompetence(request.getParameter("nom"), (Connection) request.getAttribute("connection")));
			}
		}
		
	
		if(pathInfo[1].equals("stagiaire")) {
			long id= Long.parseLong(request.getParameter("id"));
			StagiairesDao sDao= new StagiairesDao((Connection) request.getAttribute("connection"));
			if(sDao.SelectById(id)) {
				Stagiaire stagiaire= sDao.getStagiaire();
				response.getWriter().write(stagiaire.getNom()+";"+stagiaire.getPrenom());
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	private String listEntreprise(String nom, Connection connection) {
		EntrepriseDao eDao= new EntrepriseDao(connection);
		
		eDao.SelectByNomLike(nom);
		List<Entreprise> entreprises= eDao.getEntreprises();				
		
		int i= entreprises.size();
		String fichier= "[";
		for (Entreprise entreprise : entreprises) {
			fichier += "{";
			fichier += "\"Id\":\""+entreprise.getId()+"\",";
			fichier += "\"Adresse\":\""+entreprise.getAdresse()+"\",";
			fichier += "\"Ville\":\""+entreprise.getVille()+"\",";
			fichier += "\"Nom\":\""+entreprise.getNom()+"\",";
			fichier += "\"CodePostal\":\""+entreprise.getCodePostal()+"\"";
			fichier += "}";
			
			if (i != 1) {
				fichier += ",";
			}
			i--;
					
		}
		
		fichier += "]";
		
		return fichier;
	}
	
	private String listCompetence(String nom, Connection connection) {
		CompetenceDao cDao= new CompetenceDao(connection);
		
		cDao.SelectByNomLike(nom);
		List<Competence> competences= cDao.getCompetences();				
		
		int i= competences.size();
		String fichier= "[";
		for (Competence competence : competences) {
			fichier += "{";
			fichier += "\"Id\":\""+competence.getId()+"\",";
			fichier += "\"Nom\":\""+competence.getNom()+"\"";
			fichier += "}";
			if (i != 1) {
				fichier += ",";
			}
			i--;
					
		}
		
		fichier += "]";
		
		return fichier;
	}
}
