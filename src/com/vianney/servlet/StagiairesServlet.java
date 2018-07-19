package com.vianney.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.MyConnection;
import com.vianney.dao.StagiairesDao;

public class StagiairesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Stagiaire> stagiaires;
       
    public StagiairesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StagiairesDao newConnect= new StagiairesDao(new MyConnection().getConnection());
		stagiaires= newConnect.searchAll();
		request.setAttribute("page", "stagiaires");
		request.setAttribute("stagiaires", stagiaires);
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}