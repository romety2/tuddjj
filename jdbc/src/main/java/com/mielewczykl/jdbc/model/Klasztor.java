package com.mielewczykl.jdbc.model;

public class Klasztor {
	private long id;
	private Religia religia;
	private String nazwa;
	private String kontakt;

	public Klasztor() {
	}

	public Klasztor(String nazwa, String kontakt) {
		this.nazwa = nazwa;
		this.kontakt = kontakt;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Religia getReligia() {
		return religia;
	}
	public void setReligia(Religia religia) {
		this.religia = religia;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getKontakt() {
		return kontakt;
	}
	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}
}
