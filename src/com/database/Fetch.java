package com.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Orders;
import com.model.Room;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public class Fetch {

	private MySqlDB mysqlDB;

	public ObservableList<Integer> getRoom() {
		// TODO Auto-generated method stub
		mysqlDB = new MySqlDB();
		ObservableList<Integer> obList = FXCollections.observableArrayList();
		try {
			ResultSet rs = mysqlDB.executeQuery("select * from room");
			while (rs.next()) {
				obList.add(rs.getInt("room"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obList;
	}

	public ObservableList<Room> setRecord(int id, Label lRoom, Label lPersonCount, Label lSection) {
		mysqlDB = new MySqlDB();
		ObservableList<Room> ob = FXCollections.observableArrayList();
		try {
			ResultSet rs = mysqlDB.executeQuery("select * from room where id =" + id);
			while (rs.next()) {
				Room r = new Room(rs.getInt("room"), rs.getInt("person_count"), rs.getInt("section"));
				ob.add(r);
				lRoom.setText(String.valueOf(r.getRoom()));
				lPersonCount.setText(String.valueOf(r.getPersonCount()));
				lSection.setText(String.valueOf(r.getSection()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ob;
	}

	public ObservableList<Orders> setOrders(int id) {
		mysqlDB = new MySqlDB();
		ObservableList<Orders> obList = FXCollections.observableArrayList();
		try {
			ResultSet rs = mysqlDB.executeQuery(
					"select room.room, menu.name, orders.quantity, menu.price from room join orders on room.id = orders.room join menu on orders.menu = menu.id where room.room ="
							+ id);
			while (rs.next()) {
				Orders orders = new Orders(rs.getString("name"), rs.getInt("quantity"), rs.getInt("price"));
				obList.add(orders);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obList;
	}

	public ObservableList<String> setGirls(int id) {
		mysqlDB = new MySqlDB();
		ObservableList<String> obGirls = FXCollections.observableArrayList();
		try {
			ResultSet rs = mysqlDB.executeQuery(
					"select room.room, girls.name from room join invite_girls on room.id = invite_girls.room join girls on invite_girls.girls_id = girls.id where room.room ="
							+ id);
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
