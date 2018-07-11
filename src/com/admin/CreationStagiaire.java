package com.admin;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.Stagiaire;
import com.vianney.Utilisateur;

/**
 * Servlet implementation class CreationStagiaire
 */
@WebServlet( urlPatterns = "/creationstagiaire/*" )
public class CreationStagiaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationStagiaire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("titlePage", "Création d'un stagiaire");
		request.getRequestDispatcher("/WEB-INF/FormCreateStagiaire.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
//		Stagiaire user= new Stagiaire();
//		user.setNom(nom);
//		user.se
//		String nom= request.getParameter("nom");
		String prenom= request.getParameter("prenom");
		String email= (String) request.getParameter("email");
		
//		if(nom.length() < 5 || prenom.length() < 5 || )
		
		request.setAttribute("erreur", "non");
//		request.setAttribute("nom", nom);
		
//		System.out.printf("%s %s %s", nom, prenom, email);
		
		request.getRequestDispatcher("/WEB-INF/FormCreateStagiaire.jsp").forward(request, response);
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
