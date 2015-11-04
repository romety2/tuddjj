package com.mielewczykl.jdbc.model;

public class Religia {
    private long id;
    private String religia;
    private String opis;

    public Religia() {
    }

    public Religia(String religia, String opis) {
        this.religia = religia;
        this.opis = opis;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getReligia() {
        return religia;
    }
    public void setReligia(String religia) {
        this.religia = religia;
    }
    public String getOpis() {
        return opis;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }
}
