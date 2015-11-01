package com.mielewczykl.jdbc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Klasztor {

	public static void main(String[] args) {
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection db = DriverManager.getConnection("jdbc:sqlserver://eos.inf.ug.edu.pl;databaseName=lmielewczyk;trustServerCertificate=true", "lmielewczyk", "224701");
			db.close();
		}
		catch (SQLException sqle)
		{
		}
		catch (ClassNotFoundException cnfe)
		{
		}
	}
}
