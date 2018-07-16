package com.admin.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.StagiairesDao;
import com.vianney.form.ControleNewStagiaire;

/**
 * Servlet implementation class CreationStagiaire
 */
public class CreateStagiaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateStagiaire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("titlePage", "Création d'un stagiaire");
		request.setAttribute("post", false);
		request.setAttribute("page", 3);
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ControleNewStagiaire n= new ControleNewStagiaire(request);
		Stagiaire stagiaire= n.getStagiaire();
		request.setAttribute("ok", n.getOk());
		request.setAttribute("stagiaire", stagiaire);
		request.setAttribute("post", true);
		request.setAttribute("form", n);
		
		if (n.getOk()) {			
			HttpSession session = request.getSession();
	        session.setAttribute("sessionNom", stagiaire.getNom());
	        session.setAttribute("sessionPrenom", stagiaire.getPrenom());
	        StagiairesDao newConnect= new StagiairesDao((Connection) request.getAttribute("connection"));
	        long id= newConnect.newStagiaire(stagiaire);
	        
	        String url= "/ProjetEportfolio/stagiaire/"+ id;
	        request.setAttribute("url", response.encodeURL (url));
		}
		request.setAttribute("page", 3);
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
