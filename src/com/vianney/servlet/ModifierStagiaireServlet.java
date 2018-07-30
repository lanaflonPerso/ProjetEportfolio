package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.StagiairesDao;
import com.vianney.form.CtrlStagiaire;

public class ModifierStagiaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String formUrl= 		"/ProjetEportfolio/stagiaire/modifier";
	public final String page= 			"/WEB-INF/form/ModifierStagiaire.jsp";
       
    public ModifierStagiaireServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("titlePage", "Modification du compte");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		request.setAttribute("page", page);
								
		Stagiaire user= (Stagiaire) session.getAttribute("user");
		CtrlStagiaire cs= new CtrlStagiaire((Connection) request.getAttribute("connection"));
		StagiairesDao sDao= new StagiairesDao((Connection) request.getAttribute("connection"));
		long userId= user.getId();
		
		if(request.getParameter("save").equals("changer")) {
			try {
				if(cs.ctrlMdpVs(userId, request.getParameter("mdp1"), request.getParameter("mdp2"))) {
					sDao.changeMdp(userId, request.getParameter("mdp1"));
					
				} else {
					request.setAttribute("info", cs);
				}
			} catch (Exception e) {
				
			}
		} else {				
			cs.setId(userId);
			cs.ctrlNom(request.getParameter("nom"));
			cs.ctrlPrenom(request.getParameter("prenom"));
			if (!user.getEmail().equals(request.getParameter("email"))) {
				cs.ctrlEmail(request.getParameter("email"), true);
			} else {
				cs.ctrlEmail(request.getParameter("email"), false);
			}
			cs.ctrlAdresse(request.getParameter("adresse"));
			cs.ctrlDdn(request.getParameter("ddn"));
			
			if (cs.isOk()) {
				cs.setId(user.getId());
				sDao.updateStagiaire(cs.getStagiaire());
				
				session.setAttribute("user", cs.getStagiaire());
			}
			request.setAttribute("msg", cs);
		}
		
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
}
