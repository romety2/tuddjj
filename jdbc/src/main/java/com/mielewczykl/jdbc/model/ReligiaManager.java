package com.mielewczykl.jdbc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReligiaManager {

    private Connection con;
    private Statement st;

    private String polacz = "jdbc:sqlserver://eos.inf.ug.edu.pl;databaseName=lmielewczyk;trustServerCertificate=true";
    private String login = "lmielewczyk";
    private String haslo = "224701";

    private String UtworzTabele = "CREATE TABLE religia(id INT IDENTITY(1,1) PRIMARY KEY, religia VARCHAR(30), opis VARCHAR(1000));";

    private PreparedStatement dodajWartosc;
    private PreparedStatement usunWszystko;
    private PreparedStatement wyswietlWszystko;

    public ReligiaManager()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(polacz, login, haslo);
            st = con.createStatement();
            ResultSet rs = con.getMetaData().getTables(null, null, null, null);
            boolean utworzono = false;
            while (rs.next()) {
                if ("Religia".equalsIgnoreCase(rs.getString("TABLE_NAME")))
                {
                    utworzono = true;
                    break;
                }
            }
            if (!utworzono)
                st.executeUpdate(UtworzTabele);
            dodajWartosc = con.prepareStatement("INSERT INTO religia(religia, opis) VALUES (?, ?);");
            usunWszystko = con.prepareStatement("DELETE FROM religia;");
            wyswietlWszystko = con.prepareStatement("SELECT * FROM religia;");
        } catch (SQLException sqle) {
        } catch (ClassNotFoundException cnfe) {
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void UsunWszystko() {
        int i = 0;
        try {
            KlasztorManager km = new KlasztorManager();
            List<Klasztor>  klasztory = km.DajWszystkieDane();
            for (i = 0; i<klasztory.size(); i++)
                UsunZKlasztoru(klasztory.get(i));
            usunWszystko.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int DodajWartosc(Religia religia) {
        int ile = 0;
        try {
            dodajWartosc.setString(1, religia.getReligia());
            dodajWartosc.setString(2, religia.getOpis());

            ile = dodajWartosc.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ile;
    }

    public List<Religia> DajWszystkieDane() {
        List<Religia> religie = new ArrayList<Religia>();

        try {
            ResultSet rs = wyswietlWszystko.executeQuery();

            while (rs.next()) {
                Religia r = new Religia();
                r.setId(rs.getInt("id"));
                r.setReligia(rs.getString("religia"));
                r.setOpis(rs.getString("opis"));
                religie.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return religie;
    }

    public Religia DajObiektReligia(Klasztor klasztor) {
        List<Religia> religie = DajWszystkieDane();
        int i = 0;
        int ile = religie.size();
        while (religie.get(i).getId() != klasztor.getId() && i<ile)
            i++;
        if (i != ile)
            return religie.get(i);
        else
            return null;
    }

    public Religia DajPierwszaReligie() {
            List<Religia> religie = DajWszystkieDane();
            if(religie.size() != 0)
                return religie.get(0);
            else
                return null;
    }

    public void UsunZKlasztoru(Klasztor klasztor) {
        try {
            st.executeUpdate("DELETE FROM klasztor WHERE id = " + klasztor.getId() + ";");
        }
        catch (SQLException sqle) {
        }
    }
}
