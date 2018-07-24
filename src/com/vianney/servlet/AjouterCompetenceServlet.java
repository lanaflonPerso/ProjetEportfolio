package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vianney.beans.Competence;
import com.vianney.beans.Metier;
import com.vianney.beans.Stagiaire;
import com.vianney.dao.CompetenceDao;
import com.vianney.dao.MetierDao;

public class AjouterCompetenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String PAGE= 		"/WEB-INF/form/AjouterCompetence.jsp";
	public Metier metier;
	public Stagiaire stagiaire;

    public AjouterCompetenceServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Stagiaire stagiaire= (Stagiaire) session.getAttribute("user");
		
		String[] pathInfo= request.getPathInfo().split("/");
		
		try {
			long idMetier= Integer.parseInt(pathInfo[1]);
			Connection connection= (Connection) request.getAttribute("connection");
			getMetier(connection, idMetier, stagiaire.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
		request.setAttribute("titlePage", "Ajout de competence");
		request.setAttribute("metier", metier);
		request.setAttribute("page", PAGE);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		stagiaire= (Stagiaire) session.getAttribute("user");
		
String[] pathInfo= request.getPathInfo().split("/");
		
		try {
			long idMetier= Integer.parseInt(pathInfo[1]);
			Connection connection= (Connection) request.getAttribute("connection");
			CompetenceDao cDao= new CompetenceDao(connection);
			cDao.add(idMetier, request.getParameter("competence"));
		} catch (Exception e) {
			
		}
		
		request.setAttribute("page", PAGE);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
	
	private void getMetier(Connection connection, long idMetier, long idStagiaire) {
		MetierDao mDao= new MetierDao(connection);
		
		if(mDao.selectByStagiaireIdMetier(idMetier, idStagiaire)) {
			metier= mDao.getMetier();
		}
	}
}
