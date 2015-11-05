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

    private PreparedStatement PSdodaj;
    private PreparedStatement PSedytuj;
    private PreparedStatement PSusun;
    private PreparedStatement PSusunWszystko;
    private PreparedStatement PSwyswietlWszystko;

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
            PSdodaj = con.prepareStatement("INSERT INTO religia(religia, opis) VALUES (?, ?);");
            PSedytuj = con.prepareStatement("UPDATE religia SET religia = ?, opis = ?  WHERE id = ?;");
            PSusun = con.prepareStatement("DELETE FROM religia WHERE id = ? ;");
            PSusunWszystko = con.prepareStatement("DELETE FROM religia;");
            PSwyswietlWszystko = con.prepareStatement("SELECT * FROM religia;");
        } catch (SQLException sqle) {
        } catch (ClassNotFoundException cnfe) {
        }
    }

    public Connection Polaczenie() {
        return con;
    }

    public void UsunWszystko() {
        int i = 0;
        try {
            KlasztorManager km = new KlasztorManager();
            List<Klasztor>  klasztory = km.DajWszystkieDane();
            for (i = 0; i<klasztory.size(); i++)
                km.Usun(klasztory.get(i));
            PSusunWszystko.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int Dodaj(Religia religia) {
        int ile = 0;
        try {
            PSdodaj.setString(1, religia.getReligia());
            PSdodaj.setString(2, religia.getOpis());

            ile = PSdodaj.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ile;
    }

    public void Edytuj(Religia rel, String religia, String opis) {
        try {
            PSedytuj.setString(1, religia);
            PSedytuj.setString(2, opis);
            PSedytuj.setLong(3, rel.getId());

            PSedytuj.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int Usun(Religia religia) {
        int ile = 0;
        try {
            PSusun.setLong(1, religia.getId());
            ile = PSusun.executeUpdate();
        }
        catch (SQLException sqle) {
        }
        return ile;
    }

    public List<Religia> DajWszystkieDane() {
        List<Religia> religie = new ArrayList<Religia>();

        try {
            ResultSet rs = PSwyswietlWszystko.executeQuery();

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

    /*public Religia DajObiektReligia(Klasztor klasztor) {
        List<Religia> religie = DajWszystkieDane();
        int i = 0;
        int ile = religie.size();
        while (religie.get(i).getId() != klasztor.getId() && i<ile)
            i++;
        if (i != ile)
            return religie.get(i);
        else
            return null;
    }*/

    public Religia DajReligie(int id) {
            List<Religia> religie = DajWszystkieDane();
            if(religie.size() != id)
                return religie.get(id);
            else
                return null;
    }

    public List<Klasztor> pobierzKlasztory(Religia religia) {
        KlasztorManager km = new KlasztorManager();
        List<Klasztor> klasztoryWszystkie = km.DajWszystkieDane();
        List<Klasztor> klasztoryWybrane = new ArrayList<Klasztor>();
        for (int i = 0; i < klasztoryWszystkie.size(); i++)
            if(klasztoryWszystkie.get(i).getReligia().getId() == religia.getId())
                klasztoryWybrane.add(klasztoryWszystkie.get(i));
        return klasztoryWybrane;
    }

}
