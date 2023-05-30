package com.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MenuItems {
	private MySqlDB mysqlDB;

	public ObservableList<Menu> setMenu() {
		mysqlDB = new MySqlDB();
		ObservableList<Menu> obMenu = FXCollections.observableArrayList();
		try {
			ResultSet rs = mysqlDB.executeQuery("select * from menu");
			while (rs.next()) {
				Menu menu = new Menu(rs.getString("name"), rs.getInt("price"));
				obMenu.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obMenu;
	}
}
