package com.admin;

import java.io.IOException;
import java.util.HashMap;

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
		request.setAttribute("post", false);
		request.getRequestDispatcher("/WEB-INF/FormCreateStagiaire.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<String, String> myHashMap= new HashMap<String, String>();
		
		String nom= request.getParameter("nom");
		String prenom= request.getParameter("prenom");
		String email= request.getParameter("email");
		
		myHashMap.put("nom", nom);
		myHashMap.put("prenom", prenom);
		myHashMap.put("email", email);
		
		Stagiaire newStagiaire= new Stagiaire();
		newStagiaire.setNom(nom);
		if (!newStagiaire.setPrenom(prenom)) {
			myHashMap.put("classPrenom", "is-invalid");
			myHashMap.put("msgPrenom", newStagiaire.getMsgErrPrenom());
			request.setAttribute("erreur", true);
		} else {
			myHashMap.put("classPrenom", "is-valid");
		}
		
		if (!newStagiaire.setNom(nom)) {
			myHashMap.put("classNom", "is-invalid");
			myHashMap.put("msgNom", newStagiaire.getMsgErrNom());
			request.setAttribute("erreur", true);
		} else {
			myHashMap.put("classNom", "is-valid");
		}
		
		if (!newStagiaire.setEmail(email)) {
			myHashMap.put("classEmail", "is-invalid");
			myHashMap.put("msgEmail", newStagiaire.getMsgErrEmail());
			request.setAttribute("erreur", true);
		} else {
			myHashMap.put("classEmail", "is-valid");
		}
		
		System.out.println(newStagiaire.getOk());
			
		request.setAttribute("post", true);
		request.setAttribute("map", myHashMap);
		request.setAttribute("ok", newStagiaire.getOk());
		
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
