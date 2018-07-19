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
import com.vianney.form.ControleStagiaire;

public class StagiaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Stagiaire stagiaire;

    public StagiaireServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String[] pathInfo= request.getPathInfo().split("/");
		
		if(pathInfo[1].equals("modifier")) {
			request.setAttribute("page", "modifierStagiaire");
		}else {
			try {
				long id= Integer.parseInt(pathInfo[1]);
				try {
					StagiairesDao conStagiaire= new StagiairesDao((Connection) request.getAttribute("connection"));
					stagiaire= conStagiaire.CarriereStagaire(id);
					
					request.setAttribute("stagiaire", stagiaire);	
					request.setAttribute("page", "stagiaire");
					request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
				} catch (Exception e) {
					System.out.println("n'existe pas");
				}
			} catch (Exception e) {
				
			}
		}
		
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] pathInfo= request.getPathInfo().split("/");
		if(pathInfo[1].equals("modifier")) {
			if(request.getParameter("save").equals("Changer")) {
				ControleStagiaire cs= new ControleStagiaire((Connection) request.getAttribute("connection"));
				try {
					Stagiaire st= (Stagiaire) session.getAttribute("user");
					long id= st.getId();
					if(cs.ctrlMdpVd(id, request.getParameter("mdp1"), request.getParameter("mdp2"))) {
						request.setAttribute("page", "index");
						
					} else {
						request.setAttribute("info", cs);
						request.setAttribute("page", "modifierStagiaire");
					}
				} catch (Exception e) {
					request.setAttribute("page", "modifierStagiaire");
				}
			} else {
				Stagiaire user= (Stagiaire) session.getAttribute("user");
				
				ControleStagiaire cs= new ControleStagiaire((Connection) request.getAttribute("connection"));
				cs.ctrlNom(request.getParameter("nom"));
				cs.ctrlPrenom(request.getParameter("prenom"));
				cs.ctrlEmail(request.getParameter("email"), user.getEmail());
				cs.ctrlAdresse(request.getParameter("adresse"));
				cs.ctrlDdn(request.getParameter("ddn"));
				
				if (cs.isOk()) {
					StagiairesDao st= new StagiairesDao((Connection) request.getAttribute("connection"));
					cs.setId(user.getId());
					st.updateStagiaire(cs.getStagiaire());
					
					session.setAttribute("user", cs.getStagiaire());
				}
				request.setAttribute("msg", cs);
				request.setAttribute("page", "modifierStagiaire");
			}
		}
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}
}
