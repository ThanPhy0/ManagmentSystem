package com.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRoom {
	MySqlDB mysqlDB;
	PreparedStatement prepStmt;

	public void updateActiveStatus(int active, int id) {
		mysqlDB = new MySqlDB();
		try {
			prepStmt = mysqlDB.insertQuery("update room set active = ? where room = ?");
			prepStmt.setInt(1, active);
			prepStmt.setInt(2, id);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
