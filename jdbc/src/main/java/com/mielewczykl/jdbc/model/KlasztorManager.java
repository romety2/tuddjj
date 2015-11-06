package com.mielewczykl.jdbc.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KlasztorManager {

    private Connection con;
    private Statement st;

    private String url = "jdbc:jtds:sqlserver://eos.inf.ug.edu.pl;" + "databaseName=lmielewczyk" + ";user=lmielewczyk" + ";password=224701";

    private String UtworzTabele = "CREATE TABLE klasztor(id INT IDENTITY(1,1) PRIMARY KEY, id_religia INT REFERENCES religia (id), nazwa  VARCHAR(100), kontakt  VARCHAR(100));";

    private PreparedStatement PSdodaj;
    private PreparedStatement PSedytuj;
    private PreparedStatement PSusun;
    private PreparedStatement PSusunWszystko;
    private PreparedStatement PSwyswietlWszystko;

    public KlasztorManager()
    {
        try {
            con = DriverManager.getConnection(url);
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
            PSedytuj = con.prepareStatement("UPDATE klasztor SET id_religia = ?, nazwa = ?, kontakt = ? WHERE id = ?;");
            PSusun = con.prepareStatement("DELETE FROM klasztor WHERE id = ? ;");
            PSusunWszystko = con.prepareStatement("DELETE FROM klasztor;");
            PSwyswietlWszystko = con.prepareStatement("SELECT * FROM klasztor;");
        } catch (SQLException sqle) {
        }
    }

    public Connection polaczenie() {
        return con;
    }

    public void usunWszystko() {
        try {
            PSusunWszystko.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int dodaj(Klasztor klasztor) {
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

    public void edytuj(Klasztor klasztor, Religia religia, String nazwa, String kontakt) {
        try {

            PSedytuj.setLong(1, religia.getId());
            PSedytuj.setString(2, nazwa);
            PSedytuj.setString(3, kontakt);
            PSedytuj.setLong(4, klasztor.getId());

            PSedytuj.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int usun(Klasztor klasztor) {
        int ile = 0;
        try {
            PSusun.setLong(1, klasztor.getId());
            ile = PSusun.executeUpdate();
        }
        catch (SQLException sqle) {
        }
        return ile;
    }

    public List<Klasztor> dajWszystkieDane() {
        List<Klasztor> klasztory = new ArrayList<Klasztor>();

        try {
            ResultSet rs = PSwyswietlWszystko.executeQuery();
            int id, i;
            ReligiaManager rm = new ReligiaManager();
            List<Religia> religie = rm.dajWszystkieDane();
            while (rs.next()) {
                Klasztor k = new Klasztor();
                k.setId(rs.getInt("id"));
                id = rs.getInt("id_religia");
                i = 0;
                while(religie.get(i).getId() != id )
                    i++;
                k.setReligia(religie.get(i));
                k.setNazwa(rs.getString("nazwa"));
                k.setKontakt(rs.getString("kontakt"));
                klasztory.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return klasztory;
    }

    public Religia przypiszReligie(Klasztor klasztor) {
        int i = 0;
        ReligiaManager rm = new ReligiaManager();
        List<Religia> religie = rm.dajWszystkieDane();
        while (i != religie.size() && religie.get(i).getId() != klasztor.getReligia().getId())
            i++;
        if(i != religie.size())
            return religie.get(i);
        else
            return null;
    }

    public Klasztor dajKlasztor(int id) {
        List<Klasztor> klasztory = dajWszystkieDane();
        if(klasztory.size() != id)
            return klasztory.get(id);
        else
            return null;
    }

}