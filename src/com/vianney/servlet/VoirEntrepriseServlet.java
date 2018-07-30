package com.vianney.servlet;

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


@WebServlet("/entreprise/*")
public class VoirEntrepriseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String PAGE= "/WEB-INF/vue/VoirEntreprise.jsp";
       
    public VoirEntrepriseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathInfo= request.getPathInfo().split("/");
		try {
			long id= Integer.parseInt(pathInfo[1]);
			EntrepriseDao eDao= new EntrepriseDao((Connection) request.getAttribute("connection"));
			eDao.selectById(id);
			
			Entreprise entreprise= eDao.getEntreprise();
			
			StagiairesDao sDao= new StagiairesDao((Connection) request.getAttribute("connection"));
			sDao.selectStagiaireByIdEntreprise(entreprise.getId());			
			List<Stagiaire> stagiaires= sDao.getStagiaires();

			for (Stagiaire stagiaire : stagiaires) {
				System.out.println("Nom= "+stagiaire.getNom());
			}
			request.setAttribute("stagiaires", stagiaires);
			request.setAttribute("source", true);
			request.setAttribute("entreprise", entreprise);
			request.setAttribute("page", PAGE);			
		} catch (Exception e) {
			
		}
		
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
}
