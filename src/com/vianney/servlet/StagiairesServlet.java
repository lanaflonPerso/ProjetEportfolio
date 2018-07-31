package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.StagiairesDao;

public class StagiairesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String PAGE= "/WEB-INF/vue/Stagiaires.jsp";
	private List<Stagiaire> stagiaires;
       
    public StagiairesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StagiairesDao sDao= new StagiairesDao((Connection) request.getAttribute("connection"));
		sDao.searchAll();
		stagiaires= sDao.getStagiaires();
		
		com.vianney.HelperSession.direction(request, "Liste Des Stagiaires", PAGE);
		request.setAttribute("stagiaires", stagiaires);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
}