package com.admin.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.StagiairesDao;
import com.vianney.form.CtrlStagiaire;

public class CreateStagiaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String PAGE= 			"/WEB-INF/form/AjouterStagiaire.jsp";
	public final String TITRE_PAGE= 	"Création d'un stagiaire";
    
    public CreateStagiaire() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("titlePage", TITRE_PAGE);
		request.setAttribute("post", false);
		request.setAttribute("page", PAGE);
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("page", PAGE);
		request.setAttribute("titlePage", TITRE_PAGE);
		
		CtrlStagiaire ctrlS= new CtrlStagiaire((Connection) request.getAttribute("connection"));
		ctrlS.addStagiaire(request);
		Stagiaire stagiaire= ctrlS.getStagiaire();
		
		if (ctrlS.isOk()) {			
	        StagiairesDao sDao= new StagiairesDao((Connection) request.getAttribute("connection"));
	        long id= sDao.newStagiaire(stagiaire);
	        String url= request.getContextPath()+"/stagiaire/id/"+id;
	        response.sendRedirect(url);
	        return;
		}
		
		request.setAttribute("info", ctrlS);
		request.setAttribute("stagiaire", stagiaire);
		
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
}
