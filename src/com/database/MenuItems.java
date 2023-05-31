package com.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MenuItems {
	private MySqlDB mysqlDB;
	private PreparedStatement prepStmt;

	public ObservableList<Menu> setMenu() {
		mysqlDB = new MySqlDB();
		ObservableList<Menu> obMenu = FXCollections.observableArrayList();
		try {
			ResultSet rs = mysqlDB.executeQuery("select * from menu");
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setName(rs.getString("name"));
				menu.setPrice(rs.getInt("price"));
				obMenu.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obMenu;
	}

	public void add(Menu menu) {
		mysqlDB = new MySqlDB();
		try {
			prepStmt = mysqlDB.insertQuery("INSERT INTO menu(name, price) VALUES (?,?)");
			prepStmt.setString(1, menu.getName());
			prepStmt.setInt(2, menu.getPrice());
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edit(Menu menu, String name) {
		mysqlDB = new MySqlDB();
		try {
			prepStmt = mysqlDB.insertQuery("UPDATE menu SET name=?, price=? where name = ?");
			prepStmt.setString(1, menu.getName());
			prepStmt.setInt(2, menu.getPrice());
			prepStmt.setString(3, name);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
