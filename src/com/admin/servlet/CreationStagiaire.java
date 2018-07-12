package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.LogConnection;
import com.vianney.beans.Stagiaire;
import com.vianney.form.ControleNewStagiaire;

/**
 * Servlet implementation class CreationStagiaire
 */
@WebServlet( urlPatterns = "/creationstagiaire/*" )
public class CreationStagiaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LogConnection logConnection;
       
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
		
		ControleNewStagiaire n= new ControleNewStagiaire(request);
		Stagiaire stagiaire= n.getStagiaire();
		request.setAttribute("ok", n.getOk());
		request.setAttribute("stagiaire", stagiaire);
		request.setAttribute("nom", request.getParameter("nom"));
		request.setAttribute("prenom",request.getParameter("prenom"));
		request.setAttribute("email", request.getParameter("email"));
		request.setAttribute("ddn", request.getParameter("ddn"));
		request.setAttribute("post", true);
		request.setAttribute("form", n);
		
		if (n.getOk()) {
			String ligne= "";
			ligne+= stagiaire.getNom()+";";
			ligne+= stagiaire.getPrenom()+";";
			ligne+= request.getRemoteAddr() +";";
			ligne+= request.getRemoteHost();
			logConnection.ecrireLigne(ligne);
		}
		
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

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			logConnection= new LogConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void destroy() {
		super.destroy();
		LogConnection.fermer();
	}	
}
