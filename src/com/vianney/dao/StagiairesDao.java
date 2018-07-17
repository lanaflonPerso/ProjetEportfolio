package com.vianney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vianney.beans.Competence;
import com.vianney.beans.Entreprise;
import com.vianney.beans.Metier;
import com.vianney.beans.Stagiaire;

public class StagiairesDao {
	
	private final String NOM_TABLE= "Stagiaires";
	private Connection connection;
	List<Stagiaire> listStagiaire = new ArrayList<Stagiaire>();
	private Stagiaire stagiaire= new Stagiaire();
	private Metier metier;
	private Competence competence;

	public StagiairesDao(Connection connection) {
		this.connection = connection;
	}
	
//	public Stagiaire SelectByMail(String email) {
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		
//		String sql= "SELECT * FROM "+  NOM_TABLE +" WHERE Email= ?";
//		try {
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString( 1, email );
//			resultSet= preparedStatement.executeQuery();
//			createList(resultSet, false);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return stagiaire;	
//	}
	
//	public Stagiaire SelectById(Integer id) {
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		
//		String sql= "SELECT * FROM "+  NOM_TABLE +" WHERE id= ?";
//		try {
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt( 1, id );
//			resultSet= preparedStatement.executeQuery();
//			createList(resultSet, false);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		EntrepriseDao listEntreprises= new EntrepriseDao(connection);
//		List<Entreprise> entreprises= listEntreprises.SelectByStudent(id);
//		stagiaire.setEntreprises(entreprises);
//		
//		return stagiaire;	
//	}
	
//	public List<Stagiaire> searchAll() {
//		
//		String sql= "SELECT * FROM "+ NOM_TABLE;
//		
//		try {
//			Statement statement = connection.createStatement();
//			ResultSet resultSet= statement.executeQuery(sql);
//			createList(resultSet, true);
//		} catch (SQLException e) {
//			System.out.println("Problème de base de donnée");
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//		return rows;	
//	}
	
	public long newStagiaire(Stagiaire stagiaire) {
		
		long id = 0;
				
		String sql= "INSERT INTO "+ NOM_TABLE;
		sql+= 			" (Nom, Prenom, Adresse, DateNaissance, IsStagiaire, IsAdministrateur, Email)";
		sql+= " VALUES    (?,   ?,      '',       ?,             0,           0,                ?);";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			
			ps.setString(1, stagiaire.getNom());
			ps.setString(2, stagiaire.getPrenom());
			String d= formatDate (stagiaire.getDateNaissance());
			ps.setString(3, d);
			ps.setString(4, stagiaire.getEmail());
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next()) {
				id=rs.getInt(1);
				return id;
			} 
			
		} catch (SQLException e) {
			System.out.println("Problème de base de donnée");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return id;
	}
	
	public void CarriereStagaire(int stagiaireId) {
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
		
		try {
			Statement statement = connection.createStatement();
			long id_Entreprise= -1;
			long id_Stagiaire= -1;
			ResultSet resultSet= statement.executeQuery(sql);
			
			while (resultSet.next()) {
				
				if(id_Stagiaire != resultSet.getLong("Id_Stagiare")) {
					System.out.printf("Stagiaire %s %s %s\n", resultSet.getString("Nom"), resultSet.getString("Prenom"), resultSet.getString("Adresse"));
					cStagaire(resultSet);
					id_Stagiaire= resultSet.getLong("Id_Stagiare");
				}
				
				if(id_Entreprise != resultSet.getLong("Id_Entreprise")) {
					System.out.printf("Entreprise %s %s %s\n", resultSet.getString("EntrepriseNom"), resultSet.getString("EntrepriseAdresse"), resultSet.getString("EntrepriseVille"));
					stagiaire.setEntreprises(cEntreprise(resultSet));
					id_Entreprise= resultSet.getLong("Id_Entreprise");
				}
//					System.out.printf("Competence %s %s %s\n", resultSet.getString("EntrepriseNom"), resultSet.getString("EntrepriseAdresse"), resultSet.getString("EntrepriseVille"));
			}	
		} catch (SQLException e) {
			System.out.println("Problème de base de donnée");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
		
	private void cStagaire(ResultSet r) {
		try {				
			stagiaire.setId( r.getLong("Id_Stagiare"));	
			stagiaire.setNom(r.getString("Nom"));	
			stagiaire.setPrenom(r.getString("Prenom"));
			stagiaire.setEmail(r.getString("Email"));
			stagiaire.setAdresse(r.getString("Adresse"));
			stagiaire.setDateNaissance(r.getString("DateNaissance"));
		} catch (SQLException e) {
			System.out.println("2-Le Stagiaire n'est pas dispo (StagiaireDao)");
			e.printStackTrace();
		}
	}
	private Entreprise cEntreprise(ResultSet r) {
		Entreprise entreprise= new Entreprise();
		try {
			entreprise.setId(r.getLong("Id_Entreprise"));
			entreprise.setAdresse(r.getString("EntrepriseAdresse"));	
			entreprise.setVille(r.getString("EntrepriseVille"));
			entreprise.setNom(r.getString("EntrepriseNom"));
			entreprise.setCodePostal(r.getInt("EntrepriseCP"));
		} catch (SQLException e) {
			System.out.println("Erreur dans l'entreprise");
			e.printStackTrace();
		}
		return entreprise;
	}
	private void cMetier(ResultSet r) {
		long id = 0;
		
		try {
			if( id != r.getLong("Id_Metier") ) {
//				metier.setId(r.getLong("Id_Metier"));
//				
//				String[] part= r.getString("DateEntree").split("-");
//			    LocalDate localDate = LocalDate.of(Integer.parseInt(part[0]), Integer.parseInt(part[1]), Integer.parseInt(part[2]));
//			    metier.setDateEntree(localDate);
//			    
//			    
//			    part= r.getString("DateSortie").split("-");
//			    localDate = LocalDate.of(Integer.parseInt(part[0]), Integer.parseInt(part[1]), Integer.parseInt(part[2]));
//			    metier.setDateSortie(localDate);
//			    
//			    metier.setDescription(r.getString("MetierDesc"));
//			    metier.setFonction(r.getString("MetierFonc"));
//				
//				entreprise.setMetier(metier);
				
				id= r.getLong("Id_Metier");	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void cCompetence(ResultSet r) {
		long id = 0;
		
		try {
			if( id != r.getLong("Id_Competence") ) {
//				competence.setId(r.getLong("Id_Competence"));
//				competence.setNom(r.getString("CompetenceNom"));
//				
//				id= r.getLong("Id_Competence");
//				metier.setCompetence(competence);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * False renvoie un stagiaire
	 * True un une liste de stagaire
	 */
//	private void createList(ResultSet resultSet, Boolean bool) {
//		try {
//			while (resultSet.next()) {
//			    NewStagiaire.setNom(resultSet.getString("Nom"));
//			    NewStagiaire.setPrenom(resultSet.getString("Prenom"));
//			    NewStagiaire.setEmail(resultSet.getString("Email"));
//			    NewStagiaire.setAdresse(resultSet.getString("Adresse"));
//			    
//			    String[] part= resultSet.getString("DateNaissance").split("-");
//			    LocalDate localDate = LocalDate.of(Integer.parseInt(part[0]), Integer.parseInt(part[1]), Integer.parseInt(part[2]));
//			    NewStagiaire.setDateNaissance(localDate);
//			    
//			    if (bool) {
//			    	rows.add(NewStagiaire);
//			    } else {
//			    	stagaire= NewStagiaire;
//			    }
//			}
//		} catch (NumberFormatException | SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	public Stagiaire getStagiaire() {
		return stagiaire;
	}
	private String formatDate(LocalDate date) { 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = date.format(formatter);
		
		return formattedDate;
	}
}