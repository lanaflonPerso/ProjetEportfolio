package com.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.bean.Stagiaire;

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
		
		String erreur= "";
		
		String nom= request.getParameter("nom");
		System.out.println(nom);
		String prenom= request.getParameter("prenom");
		String email= request.getParameter("email");
		
		Stagiaire newStagiaire= new Stagiaire();
		if(!newStagiaire.setNom(nom))
			erreur+= "Le nom n'est pas valide (6 caractéres minimum<br>";
		if(!newStagiaire.setPrenom(prenom))
			erreur+= "Le prénom n'est pas valide (6 caractéres minimum<br>";
		if(!newStagiaire.setEmail(email))
			erreur+= "Le email n'est pas valide<br>";
				
		request.setAttribute("erreur", erreur);
		
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
