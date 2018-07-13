package com.vianney.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.dao.MyConnection;
import com.vianney.dao.StagiaireDao;

/**
 * Servlet implementation class VueStudent
 */
public class VueStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet stagiaire;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VueStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String) request.getAttribute("email");
		test(email);
		request.setAttribute("page", 5);
		request.setAttribute("stagiaire", stagiaire);
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}
	
	private void test(String email) {
		StagiaireDao newConnect= new StagiaireDao(new MyConnection().getConnection());
		stagiaire= newConnect.SelectByMail(email);
}
}
