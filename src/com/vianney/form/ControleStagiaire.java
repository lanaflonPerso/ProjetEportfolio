package com.vianney.form;

import java.sql.Connection;

import com.vianney.dao.StagiairesDao;

public class ControleStagiaire {
	private Connection connection;
	private boolean ok= true;
	private String msgMdpVs;
	
	public ControleStagiaire(Connection uConnection) {
		connection= uConnection;
	}
	
	public boolean ctrlMdpVd(long id, String mdp1, String mdp2) {
		if(mdp1.equals(mdp2)) {
			if(mdp1.length() > 7) {
								
				StagiairesDao stagiaire= new StagiairesDao(connection);				
				stagiaire.changeMdp(id, mdp1);
				return true;
			} else {
				msgMdpVs= "le mot de passe doit être egale ou supérieur a 8 caractéres";
				ok= false;
				return false;
			}
		} else {
			msgMdpVs= "les mots de passe ne sont pas identique";
			ok= false;
			return false;
		}
	}

	public boolean isOk() {
		return ok;
	}

	public String getMsgMdpVs() {
		return msgMdpVs;
	}
}
