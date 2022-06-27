package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	static String driver = "org.postgresql.Driver";
	static String url = "jdbc:postgresql://localhost:5432/ism2022";
	static String userName = "postgres";
	static String password = "sys";
	
	public static Connection getConnection() { // here get connection is a method given by us and 
		//it will return Connection
		try {
			//driver load
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url,userName,password);
			if(con!= null) {
				System.out.println("Database Successfully Connected");
			}
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

	
