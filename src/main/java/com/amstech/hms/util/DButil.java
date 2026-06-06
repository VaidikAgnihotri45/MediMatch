package com.amstech.hms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DButil {
	private final String URL = "jdbc:mysql://127.0.0.1:3306/hospital_db";
	private final String USERNAME = "root";
	private final String PASSWORD = "1234";
//	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";

	public Connection getConnection() throws SQLException,ClassNotFoundException {
		System.out.println("Connecting Mysql........");
        Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		System.out.println(connection.getCatalog());
		System.out.println("*Connected Succesfully");

		return connection;

	}

	public void getClose(Connection connection, PreparedStatement pstmt) throws SQLException {
		System.out.println("Closing Mysql connection..........");

		if (pstmt != null) {
			pstmt.close();
		}
		if (connection != null) {
			connection.close();
		}

	}
	public void getClose(Connection connection, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		System.out.println("Closing Mysql Connection..");

		if (rs != null)
			rs.close();

		if (pstmt != null)
			pstmt.close();

		if (connection != null)
			connection.close();
	}


	
}
