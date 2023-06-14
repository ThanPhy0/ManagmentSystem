package com.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckOut {
	private MySqlDB mysqlDB;
	private PreparedStatement prepStmt;
	
	public void clearThisRow(int room) {
		mysqlDB = new MySqlDB();
		try {
			prepStmt = mysqlDB.insertQuery("update room set person_count = ?, section = ?, date_time = ?, active = ?, end_section =? where room = ?");
			prepStmt.setInt(1, 0);
			prepStmt.setInt(2, 0);
			prepStmt.setDate(3, null);
			prepStmt.setInt(4, 1);
			prepStmt.setString(5, "empty");
			prepStmt.setInt(6, room);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteOrdersRow(int room) {
		mysqlDB = new MySqlDB();
		try {
			prepStmt = mysqlDB.insertQuery("delete from orders where room = ?");
			prepStmt.setInt(1, room);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteInviteGirlsRow(int room) {
		mysqlDB = new MySqlDB();
		try {
			prepStmt = mysqlDB.insertQuery("delete from invite_girls where room = ?;");
			prepStmt.setInt(1, room);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
