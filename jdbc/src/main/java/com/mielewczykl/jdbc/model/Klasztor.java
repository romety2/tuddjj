package com.mielewczykl.jdbc.model;

public class Klasztor {
	private long id;
	private String name;
	private int yob;

	public Klasztor() {
	}

	public Klasztor(String name, int yob) {
		super();
		this.name = name;
		this.yob = yob;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYob() {
		return yob;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}
}
