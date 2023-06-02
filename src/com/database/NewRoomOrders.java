package com.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.model.NewOrder;
import com.model.Room;

public class NewRoomOrders {
	private MySqlDB mysqlDB;
	private PreparedStatement prepStmt;

	public void addNewRoom(Room room) {
		mysqlDB = new MySqlDB();
		try {
			prepStmt = mysqlDB
					.insertQuery("insert into room (room, person_count, section, date_time) values (?,?,?,?)");
			prepStmt.setInt(1, room.getRoom());
			prepStmt.setInt(2, room.getPersonCount());
			prepStmt.setInt(3, room.getSection());
			prepStmt.setDate(4, Date.valueOf(room.getDate()));
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addNewOrder(NewOrder newOrder) {
		mysqlDB = new MySqlDB();
		try {
			prepStmt = mysqlDB.insertQuery("insert into orders (room, menu, quantity) values (?,?,?)");
			prepStmt.setInt(1, newOrder.getRoom());
			prepStmt.setInt(2, newOrder.getMenu());
			prepStmt.setInt(3, newOrder.getQuantity());
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addInviteGirls(NewOrder newOrder) {
		mysqlDB = new MySqlDB();
		try {
			prepStmt = mysqlDB.insertQuery("insert into invite_girls (room, girls_id) values (?,?)");
			prepStmt.setInt(1, newOrder.getRoom());
			prepStmt.setInt(2, newOrder.getGirlsId());
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
