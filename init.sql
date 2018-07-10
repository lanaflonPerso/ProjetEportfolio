DROP DATABASE IF EXISTS ProjetEportfolio;
CREATE DATABASE ProjetEportfolio;
USE ProjetEportfolio;

CREATE TABLE Utilisateurs (
	Id					int(6)			PRIMARY KEY AUTO_INCREMENT,
	Nom					VARCHAR(20)		NOT NULL,
	Prenom				VARCHAR(20)		NOT NULL,
	Adresse				VARCHAR(150)	NULL,
	DateNaissance		DATE 			NULL,
	IsStagiaire 		TINYINT(1)		NULL,
	IsAdministrateur	TINYINT(1)		NULL
) ENGINE = InnoDB;

INSERT INTO Utilisateurs(Nom, Prenom, Adresse, DateNaissance, IsStagiaire, IsAdministrateur)
				VALUES ("Vianney", "Bailleux", "31 Allée du gros chêne 59320 haubourdin", "1979-06-07", 0, 1);
INSERT INTO Utilisateurs(Nom, Prenom, Adresse, DateNaissance, IsStagiaire, IsAdministrateur)
				VALUES ("Bruce", "Dickinson", "26 rue jean jaurés 59320 haubourdin", "1984-01-20", 1, 0);

CREATE TABLE Entreprises (
	Id					int(6)			PRIMARY KEY AUTO_INCREMENT,
	Adresse				VARCHAR(150)	NOT NULL,
	Ville				VARCHAR(25)		NOT NULL,
	Nom					VARCHAR(50)		NOT NULL,
	DateEntree			Date			NOT NULL,
	DateSortie			Date			NOT NULL,
	IdUtilisateur		int(6)			REFERENCES Utilisateur(Id)
) ENGINE = InnoDB;

INSERT INTO Entreprises(Nom, Adresse, Ville, DateEntree, DateSortie, IdUtilisateur)
				VALUES ("Quinton Hazell", "24 rue des champs", "59120 Lomme", "2001-01-01", "2005-06-01", 1);

CREATE TABLE Certifications (
	Id				int(6)	PRIMARY KEY AUTO_INCREMENT,
	IdCompetence	int(6)	REFERENCES Compretences(Id),
	IdFormation		int(6)	REFERENCES Formations(Id),
	IdMetier		int(6)	REFERENCES Metiers(Id)
) ENGINE = InnoDB;

CREATE TABLE Formations (
	Id					int(6)	PRIMARY KEY AUTO_INCREMENT,
	IntituleFormation	VARCHAR(50)	NOT NULL,
	IdCompetence	int(6)	REFERENCES Compretences(Id)
) ENGINE = InnoDB;

CREATE TABLE Competences (
	Id					int(6)	PRIMARY KEY AUTO_INCREMENT,
	IntituleCompetence	VARCHAR(50) NOT NULL,

