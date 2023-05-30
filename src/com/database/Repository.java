package com.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Repository {
	public static Repository getInstance() {
		return MySqlDB.getInstance();
	}

	ResultSet executeQuery(String query) throws SQLException;

}
