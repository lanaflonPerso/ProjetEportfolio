package com.vianney.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestrictionFilter implements Filter {

    public RestrictionFilter() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession();
        
        if ( session.getAttribute("user") == null ) {
            response.sendRedirect( request.getContextPath() + "/connection" );
        } else {
            chain.doFilter( request, response );
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
