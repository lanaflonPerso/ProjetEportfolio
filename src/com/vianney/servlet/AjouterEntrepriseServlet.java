package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.dao.EntrepriseDao;
import com.vianney.form.CtrlEntreprise;

public class AjouterEntrepriseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String pageVE=			"/WEB-INF/vue/VoirEntreprise.jsp";
	public final String pageAE=			"/WEB-INF/form/AjouterEntreprise.jsp";

    public AjouterEntrepriseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("url", "/ProjetEportfolio/entreprise/ajouter");
		request.setAttribute("page", pageAE);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlEntreprise ctrlE= new CtrlEntreprise((Connection) request.getAttribute("connection"));
		ctrlE.ctrlNom(request.getParameter("nom"));
		ctrlE.ctrlAdresse(request.getParameter("adresse"));
		ctrlE.ctrlVille(request.getParameter("ville"));
		ctrlE.ctrlCp(request.getParameter("cp"));

		if (ctrlE.isOk()) {
			EntrepriseDao eDao= new EntrepriseDao((Connection) request.getAttribute("connection"));
			eDao.InsertEntreprise(ctrlE.getEntreprise());
			request.setAttribute("page", pageVE);
		} else {
			request.setAttribute("page", pageAE);
			request.setAttribute("entreprise", ctrlE.getEntreprise());
			request.setAttribute("info", ctrlE);
		}
		
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
}
