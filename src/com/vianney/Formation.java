package com.vianney;

public class Formation {
	
	private int id;
	private String intituleFormation;
	
	public Formation(String uCode, String uIntFor) {
		intituleFormation= uIntFor;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntituleFormation() {
		return intituleFormation;
	}

	public void setIntituleFormation(String intituleFormation) {
		this.intituleFormation = intituleFormation;
	}
}
