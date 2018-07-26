package com.rest;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Entreprise;
import com.vianney.beans.Stagiaire;
import com.vianney.dao.EntrepriseDao;
import com.vianney.dao.StagiairesDao;

@WebServlet("/RestServlet/*")
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RestServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Access-Control-Allow-Origin", "*");
		
		String fichier= "<?xml version = \"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>";
		fichier += "\n";
		fichier += "<list>";
		
		
		String[] pathInfo= request.getPathInfo().split("/");
		
		if(pathInfo[1].equals("entreprise")) {
			if(request.getParameter("nom") != null) {
				String nom= request.getParameter("nom");
				EntrepriseDao eDao= new EntrepriseDao((Connection) request.getAttribute("connection"));
				
				eDao.SelectByNomLike(nom);
				List<Entreprise> entreprises= eDao.getEntreprises();
				
				for (Entreprise entreprise : entreprises) {
					fichier += "\t<entreprise>\n";
					fichier += "\t\t<nom>"+entreprise.getNom()+"</nom>";
					fichier += "\t\t<ville>"+entreprise.getVille()+"</ville>";
					fichier += "\t</entreprise>";
				}
				fichier += "<list>";
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

}
