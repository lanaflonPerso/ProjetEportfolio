package com.vianney.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vianney.beans.Stagiaire;
import com.vianney.dao.MyConnection;
import com.vianney.dao.StagiaireDao;

public class ListStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Stagiaire> stagiaires;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		test();
		request.setAttribute("page", 4);
		request.setAttribute("stagiaires", stagiaires);
		request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void test() {
			StagiaireDao newConnect= new StagiaireDao(new MyConnection().getConnection());
			stagiaires= newConnect.searchAll();
	}
}