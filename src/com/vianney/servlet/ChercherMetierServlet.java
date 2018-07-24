package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vianney.beans.Metier;
import com.vianney.beans.Stagiaire;
import com.vianney.dao.MetierDao;

public class ChercherMetierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String PAGE= 		"/WEB-INF/form/ChercherMetier.jsp";
	public List<Metier> metiers= new ArrayList<>();
       
    public ChercherMetierServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Stagiaire stagiaire= (Stagiaire) session.getAttribute("user");
		
		MetierDao mDao= new MetierDao((Connection) request.getAttribute("connection"));
		if(mDao.selectLikeFonction(stagiaire.getId(), request.getParameter("recherche"))) {
			metiers= mDao.getMetiers();
			request.setAttribute("metiers", metiers);
			request.setAttribute("ok", true);
		}

		request.setAttribute("page", PAGE);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
