package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vianney.beans.Certification;
import com.vianney.beans.Formation;
import com.vianney.beans.Stagiaire;
import com.vianney.dao.FormationDao;
import com.vianney.form.CtrlFormation;

public class AjouterFormationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String PAGE=			"/WEB-INF/form/AjouterFormation.jsp";

    public AjouterFormationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		com.vianney.HelperSession.direction(request, "Ajouter une formation", PAGE);		
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		com.vianney.HelperSession.direction(request, "Ajouter une formation", PAGE);
		
		HttpSession session = request.getSession();
		Stagiaire stagiaire= (Stagiaire) session.getAttribute("user");		
		
		CtrlFormation ctrlF= new CtrlFormation((Connection) request.getAttribute("connection"), request);
		ctrlF.ctrl();
		
		if(ctrlF.isOk()) {
			FormationDao fDao= new FormationDao((Connection) request.getAttribute("connection"));
			Formation formation= ctrlF.getFormation();
			Certification certification= formation.getCertification();
			fDao.add(certification.getNom(), certification.getNiveau(), formation.getIntituleFormation(), stagiaire.getId(), ctrlF.isAvecCertification());
			
			String url= request.getContextPath() +"/stagiaire/id/"+ stagiaire.getId();
			response.sendRedirect( url );
			return;
		}
		
		request.getRequestDispatcher("/Index.jsp").forward(request, response);
	}
}
