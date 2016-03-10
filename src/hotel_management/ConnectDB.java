package hotel_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String CONN_STRING = "jdbc:mysql://localhost/";
	private static final String DB = "hotel_management";

	// Create connection
	public static Connection getConnected() {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(CONN_STRING + DB, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return connection;
	}

	// Create database
	public void createDB() throws SQLException {
		String query = "CREATE DATABASE hotel_management";

		try (Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
				Statement statement = connection.createStatement();) {

			statement.executeUpdate(query);
			System.out.println("Successfully created the database!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Create table rooms
	public void createTableRooms() throws SQLException {
		String query = "CREATE TABLE rooms (" 
				+ "number INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "type VARCHAR(20)," 
				+ "dayPrice INTEGER(4)," 
				+ "ocupied VARCHAR(5))";

		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			statement.executeUpdate(query);
			System.out.println("Table rooms created successfully!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Create table users
	public void createTableUsers() throws SQLException {

		String query = "CREATE TABLE users (" 
				+ "name VARCHAR(30)," 
				+ "surname VARCHAR(30)," 
				+ "gender VARCHAR(10),"
				+ "idCard VARCHAR(20) NOT NULL," 
				+ "age INTEGER(99)," 
				+ "roomNumber INTEGER(50),"
				+ "roomType INTEGER(10)," 
				+ "checkIn DATE ," 
				+ "userName VARCHAR(20)," 
				+ "password VARCHAR(12))";
		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			statement.executeUpdate(query);
			System.out.println("Table users created successfully!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}	
	
	//Create table services
	public static void createTableServices(){
		String query = "CREATE TABLE services (" 
				+ "idUsluge INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "nazivUsluge VARCHAR(20)," 
				+ "cijenaUsluge INTEGER(4))";

		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			statement.executeUpdate(query);
			System.out.println("Table services created successfully!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Glavna metoda(za provjeru)
	public static void main(String[] args) throws SQLException {
//samo proba da li radi
		ConnectDB db = new ConnectDB();
		db.createDB();
		db.createTableRooms();
		db.createTableUsers();
		db.createTableServices();

		System.out.println("Pozdrav");
	}
}