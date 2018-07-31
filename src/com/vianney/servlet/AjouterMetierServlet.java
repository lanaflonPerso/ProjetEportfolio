package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vianney.beans.Entreprise;
import com.vianney.beans.Metier;
import com.vianney.beans.Stagiaire;
import com.vianney.dao.EntrepriseDao;
import com.vianney.dao.MetierDao;
import com.vianney.form.CtrlMetier;

public class AjouterMetierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public final String PAGE=			"/WEB-INF/form/AjouterMetier.jsp";
	private Metier metier;
	private CtrlMetier ctrl;
	private Stagiaire stagiaire;

    public AjouterMetierServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("metier") != null) {
			try {
				long idMetier= Long.parseLong(request.getParameter("metier"));
				MetierDao mDao= new MetierDao((Connection) request.getAttribute("connection"));
				mDao.selectById(idMetier);
				Metier metier= mDao.getMetier();
				request.setAttribute("afficher", true);
				request.setAttribute("metier", metier);
				
			} catch (Exception e) {

			}
			
		}
		if (request.getParameter("entreprise") != null) {
			EntrepriseDao eDao= new EntrepriseDao((Connection) request.getAttribute("connection"));
			try {
				long id= Long.parseLong(request.getParameter("entreprise"));
				eDao.selectById(id);
				Entreprise entrerpise= eDao.getEntreprise();
				
				request.setAttribute("entreprise", entrerpise);
				request.setAttribute("source", false);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		com.vianney.HelperSession.direction(request, "Ajouter Metier", PAGE);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		stagiaire= (Stagiaire) session.getAttribute("user"); 
		
		if(ctrlMetier(request)) {				
			MetierDao mDao= new MetierDao((Connection) request.getAttribute("connection"));				
						
			if(request.getParameter("idMetier") != null) {
				metier.setId(Long.parseLong(request.getParameter("idMetier")));
			}
			
			long idMetier= mDao.add(metier, stagiaire.getId());
						
			if (request.getParameter("idEntreprise") != null) {
				long idntreprise= Long.parseLong(request.getParameter("idEntreprise"));
				mDao.addMetierEntreprise(idMetier, idntreprise);
				
				String url= request.getContextPath() +"/stagiaire/id/"+ stagiaire.getId();
				response.sendRedirect( url );
				return;
			}
			
			String url= request.getContextPath() +"/compte/metier/id/"+ idMetier;
			response.sendRedirect( url );
			return;
		}
		
		request.setAttribute("metier", metier);
		request.setAttribute("info", ctrl);
		com.vianney.HelperSession.direction(request, "Ajouter Metier", PAGE);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
	
	private boolean ctrlMetier(HttpServletRequest request) {	
		ctrl= new CtrlMetier((Connection) request.getAttribute("connection"), stagiaire);
		ctrl.ctrlFonction(request.getParameter("fonction"));
		ctrl.ctrlDescription(request.getParameter("description"));
		ctrl.ctrlDate(request.getParameter("dateE"), request.getParameter("dateS"));
		
		metier= ctrl.getMetier();
		return ctrl.isOk();
	}
}
