package com.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Girls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GirlsList {
	private MySqlDB mysqlDB;
	private PreparedStatement prepStmt;

	public ObservableList<String> listGirls() {
		mysqlDB = new MySqlDB();
		ObservableList<String> obGirls = FXCollections.observableArrayList();
		try {
			ResultSet rs = mysqlDB.executeQuery("select * from girls");
			while (rs.next()) {
				obGirls.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obGirls;
	}

	public void add(Girls girls) {
		mysqlDB = new MySqlDB();
		try {
			prepStmt = mysqlDB.insertQuery("INSERT INTO girls(name) VALUES (?)");
			prepStmt.setString(1, girls.getName());
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edit(Girls girls, String name) {
		mysqlDB = new MySqlDB();
		try {
			prepStmt = mysqlDB.insertQuery("UPDATE girls SET name=? where name = ?");
			prepStmt.setString(1, girls.getName());
			prepStmt.setString(2, name);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
