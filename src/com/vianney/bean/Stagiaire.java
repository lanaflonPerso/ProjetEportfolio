package com.vianney.bean;

<<<<<<< HEAD
=======
import java.time.LocalDateTime;
>>>>>>> 1adf6a495cc5ba058073815ef548bcc5ab6c4910
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stagiaire {
	
<<<<<<< HEAD
	private String nom;
	private String prenom;
	private String email;
	
	public String getNom() {
		return nom;
	}
	public boolean setNom(String uNom) {
		if (uNom.length() < 5) {
			this.nom = uNom;
			return true;
		} else {
			return false;
		}
	}
	public String getPrenom() {
		return prenom;
	}
	public boolean setPrenom(String uPrenom) {
		if (uPrenom.length() < 5) {
			this.prenom = uPrenom;
			return true;
		} else {
			return false;
		}
	}
	public String getEmail() {
		return email;
	}
=======
	private Long id;
	private String nom;
	private String msgErrNom;
	private String prenom;
	private String msgErrPrenom;
	private String email;
	private String msgErrEmail;
	private LocalDateTime dateNaissance;
	private String MsgErrDateNaissance;
	private boolean ok= true;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	
	public boolean setNom(String nom) {
		if (!(nom.length() < 5) ) { 
			this.nom = nom;
			return true;
		} else {
			setMsgErrNom("Le nom doit comprendre plus de 6 caractères!<br />");
			setOk();
			return false;
		}
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public boolean setPrenom(String prenom) {
		if (!(prenom.length() < 5) ) { 
			this.prenom = prenom;
			return true;
		} else {
			setMsgErrPrenom("Le prénom doit comprendre plus de 6 caractères!<br />");
			setOk();
			return false;
		}
	}

	public String getEmail() {
		return email;
	}

>>>>>>> 1adf6a495cc5ba058073815ef548bcc5ab6c4910
	public boolean setEmail(String uEmail) {
		Pattern regexMail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = regexMail.matcher(uEmail);

		if (m.find()) {
			email= uEmail;
			return true;
		} else {
<<<<<<< HEAD
			return false;
		}
	}
=======
			setMsgErrEmail("L'adresse email n'est pas valide!<br />");
			setOk();
			return false;
		}
	}

	public String getMsgErrNom() {
		return msgErrNom;
	}

	public void setMsgErrNom(String msgErrNom) {
		this.msgErrNom = msgErrNom;
	}

	public String getMsgErrPrenom() {
		return msgErrPrenom;
	}

	public void setMsgErrPrenom(String msgErrPrenom) {
		this.msgErrPrenom = msgErrPrenom;
	}

	public String getMsgErrEmail() {
		return msgErrEmail;
	}

	public void setMsgErrEmail(String msgErrEmail) {
		this.msgErrEmail = msgErrEmail;
	}
	
	public LocalDateTime getDateNaissance() {
		return dateNaissance;
	}

	public boolean setDateNaissance(String date) {
		Pattern regexDate = Pattern.compile("^[0-9]{1,2}/[0-9]{1,2}/[0-9]{2,4}$", Pattern.CASE_INSENSITIVE);
		Matcher m = regexDate.matcher(date);

		if (m.find()) {
			String[] my = date.split("/");
			int jour= Integer.parseInt(my[0]);		
			int mois= Integer.parseInt(my[1]);	
			int annee= Integer.parseInt(my[2]);
			if (jour <= 31 && mois <= 12 && annee <= 2015) {
				dateNaissance = LocalDateTime.of(annee, mois, jour, 0, 0, 0);
				return true;
			} else {
				setMsgErrDateNaissance("Format de date incorrect (07/06/79)<br />");
				setOk();
				return false;
			}
		} else {
			setMsgErrDateNaissance("Format de date incorrect (07/06/1980)<br />");
			setOk();
			return false;
		}
	}

	public String getMsgErrDateNaissance() {
		return MsgErrDateNaissance;
	}

	public void setMsgErrDateNaissance(String msgErrDateNaissance) {
		MsgErrDateNaissance = msgErrDateNaissance;
	}

	public boolean getOk() {
		return ok;
	}

	public void setOk() {
		this.ok = false;
	}	
>>>>>>> 1adf6a495cc5ba058073815ef548bcc5ab6c4910
}