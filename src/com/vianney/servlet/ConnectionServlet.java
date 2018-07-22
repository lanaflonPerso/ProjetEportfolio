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
import com.vianney.form.CtrlConnection;
import com.vianney.form.CtrlStagiaire;

public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public final String page="/WEB-INF/form/Connection.jsp";
	public Stagiaire stagiaire;

    public ConnectionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("Index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mdp= (String) request.getParameter("mdp");
		String email= (String) request.getParameter("email");
		Connection connection= (Connection) request.getAttribute("connection");

		if(ctrl(connection, mdp, email)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", stagiaire);
		} else {
			request.setAttribute("var", cc);			
			request.setAttribute("page", page);
		}
		
		request.getRequestDispatcher("Index.jsp").forward(request, response);		
	}
	
	private boolean ctrl(Connection connection, String mdp, String email) {
		StagiairesDao sDao= new StagiairesDao(connection);
		
		if(sDao.SelectByEmailMdp(mdp, email)) {
			stagiaire= sDao.getStagiaire();
			return true;
		}
		return false;
		
	}

}
