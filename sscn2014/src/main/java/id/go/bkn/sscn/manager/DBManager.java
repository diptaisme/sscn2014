package id.go.bkn.sscn.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String HOST = "localhost";
	private static String DATABASE = "dbseleksicpns";
	private static String USERNAME = "root";
	private static String PASSWORD = "adminmysql";

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName(JDBC_DRIVER);
		return (DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/"
				+ DATABASE, USERNAME, PASSWORD));
	}

}
