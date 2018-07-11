package com.vianney.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Index
 */
//@WebServlet( urlPatterns = "/" )
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String Utilisateur;
	private String Projet;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.setAttribute("Utilisateur", Utilisateur);
		request.setAttribute("Projet", Projet);
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Utilisateur= config.getInitParameter("Utilisateur");
		Projet= config.getInitParameter("Projet");		
	}

}


