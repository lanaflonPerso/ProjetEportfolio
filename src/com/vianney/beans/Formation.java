package com.vianney.beans;

public class Formation {
	
	private long id;
	private String intituleFormation;
	private Certification certification;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIntituleFormation() {
		return intituleFormation;
	}
	public void setIntituleFormation(String intituleFormation) {
		this.intituleFormation = intituleFormation;
	}
	public Certification getCertification() {
		return certification;
	}
	public void setCertification(Certification certification) {
		this.certification = certification;
	}
}
