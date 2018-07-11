package com.vianney.servlet;

import java.io.IOException;
<<<<<<< HEAD
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
=======

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
>>>>>>> cc5acc76a0b92cf9990b940c9d2b30ea2a5347f8
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Index
 */
<<<<<<< HEAD
@WebServlet( urlPatterns = "/" )
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
=======
//@WebServlet( urlPatterns = "/" )
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String Utilisateur;
	private String Projet;
>>>>>>> cc5acc76a0b92cf9990b940c9d2b30ea2a5347f8
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }
<<<<<<< HEAD
=======
    
>>>>>>> cc5acc76a0b92cf9990b940c9d2b30ea2a5347f8

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
=======
		

		request.setAttribute("Utilisateur", Utilisateur);
		request.setAttribute("Projet", Projet);
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
>>>>>>> cc5acc76a0b92cf9990b940c9d2b30ea2a5347f8
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
<<<<<<< HEAD

}
=======
	


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Utilisateur= config.getInitParameter("Utilisateur");
		Projet= config.getInitParameter("Projet");		
	}
}
>>>>>>> cc5acc76a0b92cf9990b940c9d2b30ea2a5347f8
