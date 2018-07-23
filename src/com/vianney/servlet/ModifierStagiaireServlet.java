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
	public final String pageS= 			"/WEB-INF/vue/Stagiaire.jsp";
	public final String pageC= 			"/WEB-INF/form/Connection.jsp";
       
    public ModifierStagiaireServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Modifier");
		request.setAttribute("titlePage", "Modification du compte");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
				
		if(session.getAttribute("user") != null || !session.getAttribute("user").equals("")) {
						
			Stagiaire user= (Stagiaire) session.getAttribute("user");
			long userId= user.getId();
			
			if(request.getParameter("save").equals("Changer")) {
				CtrlStagiaire cs= new CtrlStagiaire((Connection) request.getAttribute("connection"));
				try {
					Stagiaire st= (Stagiaire) session.getAttribute("user");
					long id= st.getId();
					if(cs.ctrlMdpVs(id, request.getParameter("mdp1"), request.getParameter("mdp2"))) {
						request.setAttribute("page", pageS);
						
					} else {
						request.setAttribute("info", cs);
						request.setAttribute("page", page);
					}
				} catch (Exception e) {
					request.setAttribute("page", page);
				}
			} else {				
				CtrlStagiaire cs= new CtrlStagiaire((Connection) request.getAttribute("connection"));
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
					StagiairesDao st= new StagiairesDao((Connection) request.getAttribute("connection"));
					cs.setId(user.getId());
					st.updateStagiaire(cs.getStagiaire());
					
					session.setAttribute("user", cs.getStagiaire());
					request.setAttribute("page", pageS);
				}
				request.setAttribute("msg", cs);
				request.setAttribute("page", page);
			}
		} else {
			System.out.println("je ne rentre pas");
			request.setAttribute("page", pageC);
		}
		
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
}
