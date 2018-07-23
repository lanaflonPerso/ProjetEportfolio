package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.MetierDao;
import com.vianney.form.CtrlMetier;

public class AjouterMetierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public final String page=			"/WEB-INF/form/AjouterMetier.jsp";

    public AjouterMetierServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("url", "/ProjetEportfolio/metier/ajouter");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		CtrlMetier ctrlM= new CtrlMetier((Connection) request.getAttribute("connection"));
		ctrlM.ctrlFonction(request.getParameter("fonction"));
		ctrlM.ctrlDescription(request.getParameter("description"));
		ctrlM.ctrlDateEntree(request.getParameter("dateE"));
		ctrlM.ctrlDateSortie(request.getParameter("dateS"));
		
		if(ctrlM.isOk()) {
			HttpSession session = request.getSession();
			Stagiaire stagiaire= (Stagiaire) session.getAttribute("user");
					
			MetierDao mDao= new MetierDao((Connection) request.getAttribute("connection"));
			long idMetier= mDao.ajouter(ctrlM.getMetier(), stagiaire.getId());
			request.setAttribute("page", page);
			String url= request.getContextPath() +"/metier/id/"+ idMetier;
			response.sendRedirect( url );
			return;
		}
		
		request.setAttribute("url", "/ProjetEportfolio/metier/ajouter");
		request.setAttribute("metier", ctrlM.getMetier());
		request.setAttribute("info", ctrlM);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
}
