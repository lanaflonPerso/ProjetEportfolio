package com.vianney.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.StagiairesDao;

/**
 * Servlet implementation class VueStudent
 */
public class StagiaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Stagiaire stagiaire;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StagiaireServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] pathInfo= request.getPathInfo().split("/");
		try {
			int i= Integer.parseInt(pathInfo[1]);
			try {
				StagiairesDao newConnect= new StagiairesDao((Connection) request.getAttribute("connection"));
				stagiaire= newConnect.SelectById(i);
				request.setAttribute("stagiaire", stagiaire);
				request.getRequestDispatcher("/WEB-INF/vue/Student.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println("n'existe pas");
			}
		} catch (Exception e) {
			System.out.println("integer");
		}
	}
}
