package com.vianney;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vianney.beans.Stagiaire;

public class HelperSession {

	public static Stagiaire sessionExist(HttpServletRequest request, HttpServletResponse response) {
		String page= 			"/WEB-INF/form/Connection.jsp";
		Stagiaire stagiaire = null;
		HttpSession session = request.getSession();
		
		try {
			stagiaire= (Stagiaire) session.getAttribute("user");
			System.out.println(stagiaire.getNom());
		} catch (Exception e) {
			 request.setAttribute("page", page);
			try {
				request.getRequestDispatcher("/Index.jsp").forward(request, response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return stagiaire;
	}
}
