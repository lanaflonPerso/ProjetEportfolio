package com.vianney.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Entreprise;
import com.vianney.beans.Stagiaire;
import com.vianney.dao.MyConnection;
import com.vianney.dao.StudentDao;

/**
 * Servlet implementation class VueStudent
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Stagiaire stagiaire;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
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
				test(i);
				request.setAttribute("stagiaire", stagiaire);
				request.getRequestDispatcher("/WEB-INF/vue/Student.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println("n'existe pas");
			}
		} catch (Exception e) {
			System.out.println("integer");
		}
	}
	
	private void test(Integer id) {
		StudentDao newConnect= new StudentDao(new MyConnection().getConnection());
		stagiaire= newConnect.SelectById(id);
	}
}
