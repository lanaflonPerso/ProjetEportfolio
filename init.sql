DROP DATABASE IF EXISTS ProjetEportfolio;
CREATE DATABASE ProjetEportfolio
	CHARACTER SET utf8
	COLLATE utf8_general_ci;
USE ProjetEportfolio;

CREATE TABLE Stagiaires (
	Id					int(6)			PRIMARY KEY AUTO_INCREMENT,
	Nom					VARCHAR(20)		NOT NULL,
	Prenom				VARCHAR(20)		NOT NULL,
	Email				VARCHAR(50)		NOT NULL,
	Adresse				VARCHAR(150)	NULL,
	DateNaissance		DATE 			NULL,
	MotDePasse			VARCHAR(56)		NULL,
	IsStagiaire 		TINYINT(1)		NULL,
	IsAdministrateur	TINYINT(1)		NULL
) ENGINE = InnoDB;

INSERT INTO Stagiaires (Nom, Prenom, Adresse, DateNaissance, IsStagiaire, IsAdministrateur, Email)
	VALUES 
		("Vianney", "Bailleux", "31 Allée du gros chêne 59320 haubourdin", "1979-06-07", 0, 1, "via@free.fr"),
		("Bruce", "Dickinson", "26 rue jean jaurés 59320 haubourdin", "1984-01-20", 1, 0, "bruce@free.fr"),
		("Rob", "Halford", "72, rue du Château 97480 SAINT-JOSEPH", "1944-07-23", 1, 0, "rob@free.fr"),
		("Josette", "Allain", "26, rue des Chaligny 58000 NEVERS", "1967-06-16", 1, 0, "josette@free.fr"),
		("Emmanuel", "Lagrange", "88, Rue Joseph Vernet 92220 BAGNEUX", "1984-02-12", 1, 0, "manu@free.fr");

CREATE TABLE Entreprises (
	Id					int(6)			PRIMARY KEY AUTO_INCREMENT,
	Adresse				VARCHAR(150)	NOT NULL,
	Ville				VARCHAR(25)		NOT NULL,
	Nom					VARCHAR(50)		NOT NULL,
	CodePostal			int(5)			NOT NULL
) ENGINE = InnoDB;

INSERT INTO Entreprises(Nom, Adresse, Ville, CodePostal)
	VALUES 
		("Quinton Hazell", "94, rue de Geneve", "AMIENS", "8000"),
		("Lab Photo pro", "15, place Stanislas", "NANTERRE", "9200"),
		("Food Giant", "42, rue de Geneve", "AMIENS", "8000"),
		("Pantry Pride", "15, place Stanislas", "NANTERRE", "9200"),
		("Record Town", "61, rue des six frères Ruellan", "SARREGUEMINES", "57200"),
		("Magna Gases", "95, Rue Hubert de Lisle", "LONS-LE-SAUNIER", "3900"),
		("Mr Fables", "49, avenue du Marechal Juin", "SAINT-LÔ", "5000"),
		("Dynatronics Accessories", "19, Avenue Millies Lacroix", "ÉLANCOURT", "78990"),
		("Titania", "93, rue du Faubourg National", "THIAIS", "94320"),
		("Liberal", "99, Chemin Des Bateliers", "ANGERS", "4900");

CREATE TABLE Metiers (
	Id 				int(6)		PRIMARY	KEY	AUTO_INCREMENT,
	Fonction		VARCHAR(50)	NOT NULL
) ENGINE = InnoDB;

INSERT INTO Metiers (Fonction)
	VALUES
		("développeur Java"),
		("développeur Python"),
		("développeur JavaScript"),
		("développeur Ruby"),
		("développeur C++"),
		("développeur C"),
		("développeur Go"),
		("Intégrateur Web"),
		("Photographe"),
		("Présentateur TV");

CREATE TABLE Metier_Competence (
	Id 				int(6)	PRIMARY KEY AUTO_INCREMENT,
	IdMetier		int(6)	REFERENCES Metiers(Id),
	IdCompetence	int(6)	REFERENCES Competences(Id)
) ENGINE = InnoDB;

INSERT INTO Metier_Competence (IdMetier, IdCompetence)
	VALUES
		(4, 1),
		(4, 2),
		(4, 4),
		(4, 6),
		(1, 1),
		(1, 3),
		(1, 7),
		(2, 1),
		(2, 2),
		(2, 3),
		(2, 4),
		(2, 5),
		(3, 1),
		(3, 2),
		(3, 5);

CREATE TABLE Stagiaire_Metier(
	Id				int(6)			PRIMARY KEY AUTO_INCREMENT,
	DateEntree		Date			NOT NULL,
	DateSortie		Date			NOT NULL,
	Description		VARCHAR(200)	NULL,
	IdMetier		int(6)			REFERENCES Metiers(Id),
	IdStagiaire		int(6)			REFERENCES Stagiaires(Id)
) ENGINE = InnoDB;

INSERT INTO Stagiaire_Metier(DateEntree, DateSortie, Description, IdStagiaire, IdMetier)
	VALUES
		("1999-01-01", "2005-10-20", "Création de la maquette du site en HTML5", 0, 2),
		("2006-05-09", "2009-11-05", "Mise en place du code en Python 3.6", 1, 1),
		("1997-08-15", "1999-08-03", "Création de la partie DAO", 2, 3),
		("2000-08-01", "2010-01-15", "Maintenance Partie DAO", 2, 4);

CREATE TABLE Stagiaire_Formation (
	Id 				int(6)	PRIMARY KEY	AUTO_INCREMENT,
	IdStagiaire		int(6)	REFERENCES Stagiaires(Id),
	IdFormation		int(6)	REFERENCES Formations(Id)
) ENGINE = InnoDB;

INSERT INTO Stagiaire_Formation(IdStagiaire, IdFormation)
	VALUES
		(4, 1),
		(4, 2),
		(1, 4),
		(1, 5),
		(2, 3),
		(2, 5),
		(3, 0),
		(3, 1),
		(3, 2);

CREATE TABLE Certification_Competence (
	Id				int(6)	PRIMARY KEY AUTO_INCREMENT,
	IdCertification int(6)	REFERENCES Certifications(Id),
	IdCompetence	int(6)	REFERENCES Competences(Id)
) ENGINE = InnoDB;

INSERT INTO Certification_Competence (IdCertification, IdCompetence)
	VALUES
		(5, 3),
		(5, 1),
		(5, 2),
		(1, 3),
		(1, 5),
		(2, 0),
		(2, 3),
		(2, 6),
		(3, 2),
		(3, 5),
		(4, 2),
		(4, 3),
		(4, 6),
		(4, 0);

CREATE TABLE Competences(
	Id				int(6)		PRIMARY KEY AUTO_INCREMENT,
	Nom				VARCHAR(60)	NOT NULL
) ENGINE = InnoDB;

INSERT INTO Competences(Nom)
	VALUES
		("Crée un squelette HTML5"),
		("Crée Script JS"),
		("Compétence 3"),
		("Compétence 4"),
		("Compétence 5"),
		("Compétence 6"),
		("Compétence 7"),
		("Compétence 8"),
		("Compétence 9"),
		("Compétence 10");

CREATE TABLE Certifications (
	Id				int(6)		PRIMARY KEY AUTO_INCREMENT,
	Nom				VARCHAR(50)	NOT NULL,
	Niveau			int(1)		NOT NULL
) ENGINE = InnoDB;

INSERT INTO Certifications(Nom, Niveau)
	VALUES
		("Certification AXELOS", 1),
		("Certification AXELOS", 2),
		("Certification AXELOS", 3),
		("certification Voltaire", 1),
		("certification Voltaire", 2),
		("certification Voltaire", 3),
		("certification TOEIC", 1),
		("certification TOEIC", 2),
		("certification TOEIC", 3),
		("certification Bulats", 1),
		("certification Bulats", 2),
		("certification Bulats", 3);

CREATE TABLE Formations (
	Id					int(6)		PRIMARY KEY AUTO_INCREMENT,
	IntituleFormation	VARCHAR(50)	NOT NULL,
	IdCertification		int(6)		REFERENCES Formations(Id)
) ENGINE = InnoDB;

INSERT INTO Formations (IntituleFormation, IdCertification)
	VALUES
		("Windows débutant", 11),
		("Internet débutant", 8),
		("Windows débutant", 6),
		("Technicien maintenance informatique", 4),
		("Technique de développement web", 6),
		("réseaux et télécommunications", 3),
		("Assistance en informatique", 4);

CREATE TABLE Metier_Entreprise (
	Id  			int(6)		PRIMARY KEY AUTO_INCREMENT,
	IdMetier		int(6)		REFERENCES Metiers(Id),
	IdEntreprise	int(6)		REFERENCES Entreprises(Id)
) ENGINE = InnoDB;

INSERT INTO Metier_Entreprise (IdMetier, IdEntreprise)
	VALUES
		(4, 3),
		(3, 1);