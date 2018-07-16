package com.vianney.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.admin.LogConnection;

/**
 * Servlet Filter implementation class LogCreateStagiaire
 */
public class LogCreateStagiaire implements Filter {

	private LogConnection logConnection= new LogConnection();;
	
    public LogCreateStagiaire() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LogConnection.fermer();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String ligne= "";
		ligne+= request.getParameter("nom")+";";
		ligne+= request.getParameter("prenom")+";";
		ligne+= request.getRemoteAddr() +";";
		ligne+= request.getRemoteHost();
		logConnection.ecrireLigne(ligne);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
