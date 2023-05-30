package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDB implements Repository {

	static final String URL = "jdbc:mysql://localhost:3306/test";
	static final String User = "user";
	static final String Pass = "password";

	public static Repository repo = new MySqlDB();

	Connection con;
	PreparedStatement prepStmt;
	Statement stmt;

	public static Repository getInstance() {
		return repo;
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, User, Pass);

	}

	@Override
	public ResultSet executeQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		try {
			con = getConnection();
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stmt.executeQuery(query);
	}
}