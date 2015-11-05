package com.mielewczykl.jdbc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KlasztorManager {

    private Connection con;
    private Statement st;

    private String polacz = "jdbc:sqlserver://eos.inf.ug.edu.pl;databaseName=lmielewczyk;trustServerCertificate=true";
    private String login = "lmielewczyk";
    private String haslo = "224701";

    private String UtworzTabele = "CREATE TABLE klasztor(id INT IDENTITY(1,1) PRIMARY KEY, id_religia INT REFERENCES religia (id), nazwa  VARCHAR(100), kontakt  VARCHAR(100));";

    private PreparedStatement PSdodaj;
    private PreparedStatement PSusun;
    private PreparedStatement PSusunWszystko;
    private PreparedStatement PSwyswietlWszystko;

    public KlasztorManager()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(polacz, login, haslo);
            st = con.createStatement();
            ResultSet rs = con.getMetaData().getTables(null, null, null, null);
            boolean utworzono = false;
            while (rs.next()) {
                if ("Klasztor".equalsIgnoreCase(rs.getString("TABLE_NAME")))
                {
                    utworzono = true;
                    break;
                }
            }
            if (!utworzono)
                st.executeUpdate(UtworzTabele);
            PSdodaj = con.prepareStatement("INSERT INTO klasztor(id_religia, nazwa, kontakt) VALUES (?, ?, ?);");
            PSusun = con.prepareStatement("DELETE FROM klasztor WHERE id = ? ;");
            PSusunWszystko = con.prepareStatement("DELETE FROM klasztor;");
            PSwyswietlWszystko = con.prepareStatement("SELECT * FROM klasztor;");
        } catch (SQLException sqle) {
        } catch (ClassNotFoundException cnfe) {
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void UsunWszystko() {
        try {
            PSusunWszystko.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int Dodaj(Klasztor klasztor) {
        int ile = 0;
        try {
            PSdodaj.setLong(1, klasztor.getReligia().getId());
            PSdodaj.setString(2, klasztor.getNazwa());
            PSdodaj.setString(3, klasztor.getKontakt());

            ile = PSdodaj.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ile;
    }

    public int Usun(Klasztor klasztor) {
        int ile = 0;
        try {
            PSusun.setString(1, Long.toString(klasztor.getId()));

            ile = PSusun.executeUpdate();
        }
        catch (SQLException sqle) {
        }
        return ile;
    }

    public List<Klasztor> DajWszystkieDane() {
        List<Klasztor> klasztory = new ArrayList<Klasztor>();

        try {
            ResultSet rs = PSwyswietlWszystko.executeQuery();
            ReligiaManager rm = new ReligiaManager();
            while (rs.next()) {
                Klasztor k = new Klasztor();
                k.setId(rs.getInt("id"));
                k.setReligia(rm.DajPierwszaReligie());
                k.setNazwa(rs.getString("nazwa"));
                k.setKontakt(rs.getString("kontakt"));
                klasztory.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return klasztory;
    }

}