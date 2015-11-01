package com.mielewczykl.jdbc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Klasztor {
	Klasztor() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection db = DriverManager.getConnection("jdbc:sqlserver://eos.inf.ug.edu.pl;databaseName=lmielewczyk;trustServerCertificate=true", "lmielewczyk", "224701");
			Statement st = db.createStatement();
			st.execute("INSERT INTO religia(religia, opis) VALUES ('string', 'string');");
			st.execute("INSERT INTO klasztor(id_religia, nazwa, kontakt) VALUES (1, 'string', 'string');");
			st.close();
			db.close();
			}
		catch (SQLException sqle)
		{
		}
		catch (ClassNotFoundException cnfe)
		{
		}
	}
	public static void main(String[] args)
	{
		new Klasztor();
	}
}
