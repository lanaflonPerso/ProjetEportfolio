package com.vianney.beans;

<<<<<<< HEAD
public class Metier {
	private long id;
	private String fonction;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
=======
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Metier {
	private long id;
	private LocalDate dateEntree;
	private LocalDate dateSortie;
	private String fonction;
	private String description;
	private List<Competence> competence= new ArrayList<Competence>();
	
	public LocalDate getDateEntree() {
		return dateEntree;
	}
	public void setDateEntree(LocalDate dateEntree) {
		this.dateEntree = dateEntree;
	}
	public LocalDate getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
>>>>>>> b52f73ee2321e34ce59a23d67a9381c2b1ad9e31
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
<<<<<<< HEAD
=======
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Competence> getCompetence() {
		return competence;
	}
	public void setCompetence(Competence competence) {
		this.competence.add(competence);
	}
	
>>>>>>> b52f73ee2321e34ce59a23d67a9381c2b1ad9e31
}
