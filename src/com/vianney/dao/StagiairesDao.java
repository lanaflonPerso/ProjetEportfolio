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
	
	private Connection connection;
	private List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
	private Stagiaire stagiaire = new Stagiaire();
	private boolean ok= true;
	
	public StagiairesDao(Connection connection) {
		this.connection = connection;
	}
	
	public boolean VerifMail(String email) {
		String sql= "SELECT * FROM Stagiaires WHERE email= ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql); 
			ps.setString(1, email);
			ResultSet r= ps.executeQuery();
			
			if (!r.next()) {
				return false;
			}
			r.beforeFirst();
			createList(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public Stagiaire SelectById(long id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql= "SELECT * FROM Stagiaires WHERE id= ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong( 1, id );
			resultSet= preparedStatement.executeQuery();
			createList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stagiaires.get(0);
	}
	

	public Stagiaire SelectByMail(String email) {
		String sql= "SELECT * FROM Stagiaires WHERE email= ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql); 
			ps.setString(1, email);
			ResultSet r= ps.executeQuery();
			createList(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stagiaires.get(0);
	}
	
	public List<Stagiaire> searchAll() {
		ResultSet resultSet = null;
		
		String sql= "SELECT * FROM Stagiaires";
		try {
			Statement statement= connection.createStatement();
			resultSet= statement.executeQuery(sql);
			createList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stagiaires;
	}
	
	public long newStagiaire(Stagiaire stagiaire) {
		
		long id = 0;
				
		String sql= "INSERT INTO  Stagiaires";
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
	
	public Stagiaire SelectByEmailMdp(String mdp, String email) {
		String sql= "SELECT * FROM Stagiaires WHERE email= ? AND MotDePasse= ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql); 
			ps.setString(1, email);
			ps.setString(2, mdp);
			ResultSet r= ps.executeQuery();
						
			if (!r.next()) {
				return stagiaire;
			}
			r.beforeFirst();
			createList(r);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stagiaires.get(0);
	}
	
	public boolean updateStagiaire(Stagiaire stagiaire) {
		String sql= "UPDATE Stagiaires ";
		sql+= "SET Nom= ?, Prenom= ?, Email= ?, Adresse= ?, DateNaissance= ? ";
		sql+= "WHERE Id= ?";
		
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, stagiaire.getNom());
			ps.setString(2, stagiaire.getPrenom());
			ps.setString(3, stagiaire.getEmail());
			ps.setString(4, stagiaire.getAdresse());
			String d= formatDate (stagiaire.getDateNaissance());
			ps.setString(5, d);
			ps.setLong(6, stagiaire.getId());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public Stagiaire CarriereStagaire(long id) {
		
		Stagiaire stagiaire = null;
		
		String sql= "SELECT	SM.DateEntree, SM.DateSortie, SM.Description AS MetierDesc, ";
		sql+= "S.Id AS Id_Stagiaire, S.Nom, S.Prenom, S.Email, S.Adresse, S.DateNaissance, ";
		sql+= "M.Id AS Id_Metier, M.Fonction AS MetierFonc, ";
		sql+= "E.Id AS Id_Entreprise, E.Adresse AS EntrepriseAdresse, E.Ville AS EntrepriseVille, ";
		sql+= "E.Nom AS EntrepriseNom, E.CodePostal AS EntrepriseCP, ";
		sql+= "C.Id AS Id_Competence, C.Nom AS CompetenceNom, ";
		sql+= "M.Id AS Id_Metier, M.Fonction ";
		sql+= "FROM Stagiaire_Metier AS SM, ";
		sql+= "Stagiaires AS S, ";
		sql+= "Metiers AS M, ";
		sql+= "Entreprises AS E, ";
		sql+= "Metier_Competence AS MC, ";
		sql+= "Competences AS C, ";
		sql+= "Metier_Entreprise AS ME ";
		sql+= "WHERE S.Id= ? AND SM.IdStagiaire= S.Id ";
		sql+= "AND M.Id= SM.IdMetier ";
		sql+= "AND MC.IdMetier= M.Id ";
		sql+= "	AND C.Id= MC.IdCompetence ";
		sql+= "AND ME.IdMetier= M.Id AND ME.IdEntreprise= E.Id ";
		sql+= "ORDER BY M.Id, E.Id, C.Id;";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			ps.setLong(1, id);
			ResultSet r= ps.executeQuery();
			
			long idStagiaire = -1;
			long idCompetence = -1;
			long idMetier= -1;
			long idEntreprise= -1;
			List<Entreprise> entreprises= new ArrayList<>();
			List<Competence> competences= new ArrayList<>();
			
			while (r.next()) {
				
				if (idCompetence != r.getLong("Id_Competence")) {
					Competence competence= new Competence();
					competence.setId(r.getLong("Id_Competence"));
					competence.setNom(r.getString("CompetenceNom"));
					
					System.out.println("J'ajoute la competence "+ r.getString("CompetenceNom"));
					competences.add(competence);
					idCompetence= r.getLong("Id_Competence");
					
					if(idMetier != r.getLong("Id_Metier")) {
						Metier metier= new Metier();
						metier.setId(r.getLong("Id_Metier"));
						metier.setDateEntree(r.getString("DateEntree"));
						metier.setDateSortie(r.getString("DateSortie"));
						metier.setFonction(r.getString("Fonction"));
						metier.setDescription(r.getString("MetierDesc"));
						
						metier.setCompetence(competences);
						competences.clear();
						System.out.println("j'fface mon tableau");
						idMetier= r.getLong("Id_Metier");
						
						if(idEntreprise != r.getLong("Id_Entreprise")) {
							Entreprise entreprise= new Entreprise();
							
							entreprise.setId(r.getLong("Id_Entreprise"));
							entreprise.setNom(r.getString("EntrepriseNom"));
							entreprise.setAdresse(r.getString("EntrepriseAdresse"));
							entreprise.setCodePostal(r.getInt("EntrepriseCP"));
							entreprise.setVille(r.getString("EntrepriseVille"));
							
							entreprises.add(entreprise);
							entreprise.setListMetier(metier);
							idEntreprise= r.getLong("Id_Entreprise");
							
							if (idStagiaire != r.getLong("Id_Stagiaire")) {
								stagiaire= new Stagiaire();
								stagiaire.setId(r.getLong("Id_Stagiaire"));
								stagiaire.setAdresse(r.getString("Adresse"));
								stagiaire.setNom(r.getString("Nom"));
								stagiaire.setPrenom(r.getString("Prenom"));
							
								stagiaire.setEntreprises(entreprises);
								idStagiaire= r.getLong("Id_Stagiaire");
							}
						}
					}
				}
			}	
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
		return stagiaire;
	}
	
	public void changeMdp(long id, String mdp) {
	
		String sql= "UPDATE Stagiaires SET MotDePasse = ? WHERE id= ?";
		
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, mdp);
			ps.setLong(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void createList(ResultSet r) {
		try {
			while (r.next()) {
			    stagiaire.setId(r.getLong("Id"));
			    stagiaire.setNom(r.getString("Nom"));
			    stagiaire.setPrenom(r.getString("Prenom"));
			    stagiaire.setEmail(r.getString("Email"));
			    stagiaire.setAdresse(r.getString("Adresse"));
			    stagiaire.setDateNaissance(r.getString("DateNaissance"));
			    
			    stagiaires.add(stagiaire); 
			}    			
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}	

	private String formatDate (LocalDate date) { 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = date.format(formatter);
		
		return formattedDate;
	}

	public boolean isOk() {
		return ok;
	}

}