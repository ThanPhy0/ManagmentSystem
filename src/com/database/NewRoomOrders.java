package com.database;

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
					.insertQuery("insert into room (room, person_count, section, date_time) values (?,?,?,Now())");
			prepStmt.setInt(1, room.getRoom());
			prepStmt.setInt(2, room.getPersonCount());
			prepStmt.setInt(3, room.getSection());
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// For take exist room
	public void updateRoom(Room room, int roomId) {
		mysqlDB = new MySqlDB();
		try {
			prepStmt = mysqlDB.insertQuery(
					"update room set person_count=?, section=?, date_time=Now(), active=?, end_section=? where room=?");
			prepStmt.setInt(1, room.getPersonCount());
			prepStmt.setInt(2, room.getSection());
			prepStmt.setInt(3, room.getActiveStatus());
			prepStmt.setString(4, room.getEndSection());
			prepStmt.setInt(5, roomId);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// This method can use for new room create and take exist room!
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

	// This method can use for new room create and take exist room!
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
