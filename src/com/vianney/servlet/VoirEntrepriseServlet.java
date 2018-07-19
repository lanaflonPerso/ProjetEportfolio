package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Entreprise;
import com.vianney.dao.EntrepriseDao;

/**
 * Servlet implementation class VoirEntrepriseServlet
 */
@WebServlet("/VoirEntrepriseServlet")
public class VoirEntrepriseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private long id= 1;

    public VoirEntrepriseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathInfo= request.getPathInfo().split("/");
		try {
			id= Integer.parseInt(pathInfo[1]);
		} catch (Exception e) {

		}
		
		EntrepriseDao eDao= new EntrepriseDao((Connection) request.getAttribute("connection"));
		System.out.println(id);
		eDao.selectById(id);
		Entreprise e= eDao.getEntreprise();
		System.out.println("Nom= "+ e.getNom());
		System.out.println("Adresse= "+ e.getAdresse());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
