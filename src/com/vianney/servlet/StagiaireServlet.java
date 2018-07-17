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
		
		String sql= "SELECT	SM.DateEntree, SM.DateSortie, SM.Description AS MetierDesc, ";
		sql+= "S.Id AS Id_Stagiare, S.Nom, S.Prenom, S.Email, S.Adresse, S.DateNaissance, ";
		sql+= "M.Id AS Id_Metier, M.Fonction AS MetierFonc, ";
		sql+= "E.Id AS Id_Entreprise, E.Adresse AS EntrepriseAdresse, E.Ville AS EntrepriseVille, ";
		sql+= "E.Nom AS EntrepriseNom, E.CodePostal AS EntrepriseCP, ";
		sql+= "C.Id AS Id_Competence, C.Nom AS CompetenceNom ";
		sql+= "FROM Stagiaire_Metier AS SM, ";
		sql+= "Stagiaires AS S, ";
		sql+= "Metiers AS M, ";
		sql+= "Entreprises AS E, ";
		sql+= "Metier_Competence AS MC, ";
		sql+= "Competences AS C, ";
		sql+= "metier_entreprise AS ME ";
		sql+= "WHERE S.Id= 2 AND SM.IdStagiaire= 2 ";
		sql+= "AND M.Id= SM.IdMetier ";
		sql+= "AND MC.IdMetier= M.Id ";
		sql+= "	AND C.Id= MC.IdCompetence ";
		sql+= "AND ME.IdMetier= M.Id AND ME.IdEntreprise= E.Id;";
		
		System.out.println("\n"+sql+"\n");
		
		String[] pathInfo= request.getPathInfo().split("/");
		try {
			long id= Integer.parseInt(pathInfo[1]);
			try {
				StagiairesDao newConnect= new StagiairesDao((Connection) request.getAttribute("connection"));
				stagiaire= newConnect.SelectById(id);
				request.setAttribute("stagiaire", stagiaire);
				request.setAttribute("page", "stagiaire");
				request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println("n'existe pas");
			}
		} catch (Exception e) {
			System.out.println("integer");
		}
	}
}
