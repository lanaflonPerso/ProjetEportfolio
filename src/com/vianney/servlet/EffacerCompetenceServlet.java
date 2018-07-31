package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.CompetenceDao;

public class EffacerCompetenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EffacerCompetenceServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Stagiaire stagiaire= (Stagiaire) session.getAttribute("user");
		
		String[] pathInfo= request.getPathInfo().split("/");
		
		try {
			long idCompetence= Integer.parseInt(pathInfo[1]);
			long idMetier= Integer.parseInt(pathInfo[2]);
			CompetenceDao cDao= new CompetenceDao((Connection) request.getAttribute("connection"));
			cDao.effacer(idCompetence, idMetier);
		} catch (Exception e) {

		}
		
		String url= request.getContextPath() +"/stagiaire/id/"+ stagiaire.getId();
		response.sendRedirect( url );
		return;
	}
}
