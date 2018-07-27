package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vianney.beans.Metier;
import com.vianney.beans.Stagiaire;
import com.vianney.dao.MetierDao;
import com.vianney.form.CtrlMetier;

public class AjouterMetierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public final String PAGE=			"/WEB-INF/form/AjouterMetier.jsp";
	private Metier metier;
	private CtrlMetier ctrl;

    public AjouterMetierServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("page", PAGE);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(ctrlMetier(request)) {
			HttpSession session = request.getSession();
			Stagiaire stagiaire= (Stagiaire) session.getAttribute("user");
					
			MetierDao mDao= new MetierDao((Connection) request.getAttribute("connection"));
			
			long idMetier= mDao.add(metier, stagiaire.getId());
			String url= request.getContextPath() +"/compte/metier/id/"+ idMetier;
			response.sendRedirect( url );
			return;
		}
		
		request.setAttribute("metier", metier);
		request.setAttribute("info", ctrl);
		request.setAttribute("page", PAGE);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
	
	private boolean ctrlMetier(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Stagiaire stagiaire= (Stagiaire) session.getAttribute("user"); 
		
		CtrlMetier ctrl= new CtrlMetier((Connection) request.getAttribute("connection"), stagiaire);
		ctrl.ctrlFonction(request.getParameter("fonction"));
		ctrl.ctrlDescription(request.getParameter("description"));
		ctrl.ctrlDateEntree(request.getParameter("dateE"));
		ctrl.ctrlDateSortie(request.getParameter("dateS"));
		
		metier= ctrl.getMetier();
		
		return ctrl.isOk();
	}
}
