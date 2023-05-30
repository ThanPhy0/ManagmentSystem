package com.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GirlsList {
	private MySqlDB mysqlDB;

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
}
