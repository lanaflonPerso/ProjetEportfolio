package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vianney.beans.Entreprise;
import com.vianney.beans.Stagiaire;
import com.vianney.dao.EntrepriseDao;
import com.vianney.dao.MetierDao;

public class LierEntrepriseMetierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String page=			"/WEB-INF/form/LierEntrepriseMetier.jsp";
	public final String pageC= 			"/WEB-INF/form/Connection.jsp";
	public final String pageS= 			"/WEB-INF/vue/Stagiaire.jsp";
	public MetierDao mDao;
	public long idMetier;
	
    public LierEntrepriseMetierServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Stagiaire stagiaire= (Stagiaire) session.getAttribute("user");	
		
		request.setAttribute("page", page);
		
		String[] pathInfo= request.getPathInfo().split("/");
		
		try {
			long idEntreprise= Long.parseLong(request.getParameter("entreprise"));
			mDao.addMetierEntreprise(idMetier, idEntreprise);
			request.setAttribute("page", pageS);
		} catch (Exception e) {
			
		}
		
		try {
			idMetier= Integer.parseInt(pathInfo[1]);
			
			mDao= new MetierDao((Connection) request.getAttribute("connection"));
			mDao.selectByStagiaireIdMetier(idMetier, stagiaire.getId());
			
			request.setAttribute("idMetier", idMetier);
			EntrepriseDao eDao= new EntrepriseDao((Connection) request.getAttribute("connection"));
			if(!request.getParameter("cp").equals("")) {
				eDao.likeCp(request.getParameter("cp"));
			}
			
			if(!request.getParameter("nom").equals("")) {
				eDao.likeNom(request.getParameter("nom"));
			}
			
			if (eDao.select()) {
				List<Entreprise> entreprises= eDao.getEntreprises();
				request.setAttribute("idmetier", idMetier);
				request.setAttribute("ok", true);
				request.setAttribute("entreprises", entreprises);	
			}
			request.setAttribute("page", page);
			
		} catch (Exception e) {
				
		}
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
