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

    private PreparedStatement dodajWartosc;
    private PreparedStatement usunWszystko;
    private PreparedStatement wyswietlWszystko;

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
            dodajWartosc = con.prepareStatement("INSERT INTO klasztor(id_religia, nazwa, kontakt) VALUES (?, ?, ?);");
            usunWszystko = con.prepareStatement("DELETE FROM Klasztor;");
            wyswietlWszystko = con.prepareStatement("SELECT id, id_religia, nazwa, kontakt FROM Klasztor;");
        } catch (SQLException sqle) {
        } catch (ClassNotFoundException cnfe) {
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void UsunWszystko() {
        try {
            usunWszystko.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int DodajWartosc(Klasztor klasztor) {
        int count = 0;
        try {
            //dodajWartosc.setInt(1, klasztor.getReligia()); -- relacja
            dodajWartosc.setString(2, klasztor.getNazwa());
            dodajWartosc.setString(3, klasztor.getKontakt());

            count = dodajWartosc.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Klasztor> DajWszystkieDane() {
        List<Klasztor> klasztory = new ArrayList<Klasztor>();

        try {
            ResultSet rs = wyswietlWszystko.executeQuery();

            while (rs.next()) {
                Klasztor k = new Klasztor();
                k.setId(rs.getInt("id"));
                //k.setReligia(rs.getInt("id_religia")); -relacja
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