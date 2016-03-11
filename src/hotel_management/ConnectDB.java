package hotel_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectDB {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String CONN_STRING = "jdbc:mysql://localhost/";
	private static final String DB = "hotel_management";

	// Create connection
	public static Connection getConnected() {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(CONN_STRING + DB,
					USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return connection;
	}

	// Create database
	public void createDB() throws SQLException {
		String query = "CREATE DATABASE hotel_management";

		try (Connection connection = DriverManager.getConnection(CONN_STRING,
				USERNAME, PASSWORD);
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
				+ "type VARCHAR(20)," + "dayPrice INTEGER(4),"
				+ "occupied BIT)";

		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			statement.executeUpdate(query);
			System.out.println("Table rooms created successfully!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Create table users
	public void createTableUsers() throws SQLException {

		String query = "CREATE TABLE users (" + "name VARCHAR(30),"
				+ "surname VARCHAR(30)," + "gender VARCHAR(10),"
				+ "idCard VARCHAR(20) NOT NULL," + "age INTEGER(99),"
				+ "roomNumber INTEGER(50)," + "roomType INTEGER(10),"
				+ "checkIn DATE ," + "userName VARCHAR(20),"
				+ "password VARCHAR(12))";
		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			statement.executeUpdate(query);
			System.out.println("Table users created successfully!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Create table services
	public void createTableServices() throws SQLException {
		String query = "CREATE TABLE services ("
				+ "idUsluge INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "nazivUsluge VARCHAR(20)," + "cijenaUsluge INTEGER(4))";

		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			statement.executeUpdate(query);
			System.out.println("Table services created successfully!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Insert object into table rooms
	public void updateRoom(Room room) throws SQLException {
		String query = "INSERT INTO rooms(number, type, dayPrice, occupied) VALUES("
				+ room.getNumber()
				+ ", '"
				+ room.getType()
				+ "', "
				+ room.getDayPrice() + ", " + room.getOccupied() + ")";

		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			statement.executeUpdate(query);
			System.out.println("Table rooms updated successfully!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Insert object into table users
	public void updateUser(User user) throws SQLException {
		String query = "INSERT INTO users(name, surname, gender, idCard, age, roomNumber, roomType,  checkIn, userName, password) VALUES('"
				+ user.getName()
				+ "', '"
				+ user.getSurname()
				+ "', '"
				+ user.getGender()
				+ "', '"
				+ user.getIdCard()
				+ "', "
				+ user.getAge()
				+ ", "
				+ user.getRoomNumber()
				+ ", "
				+ user.getRoomType()
				+ ", '"
				+ user.getCheckIn()
				+ "', '"
				+ user.getUserName() + "', '" + user.getPassword() + "')";

		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			statement.executeUpdate(query);
			System.out.println("Table users updated successfully!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Insert object into table Usluge
	public void insertUsluge(Usluge usluge) throws SQLException {
		String query = "INSERT INTO services(idUsluge,nazivUsluge,cijenaUsluge) VALUES("
				+ usluge.getIdUsluge()
				+ ", '"
				+ usluge.getNazivUsluge()
				+ "', " + usluge.getCijenaUsluge() + ")";

		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			statement.executeUpdate(query);
			System.out.println("Table services updated successfully!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Updates an element in table usluge
	public void updateUsluge(Usluge usluge) throws SQLException {
		String query = "UPDATE services SET nazivUsluge='"
				+ usluge.getNazivUsluge() + "', cijenaUsluge="
				+ usluge.getCijenaUsluge() + " WHERE idUsluge="
				+ usluge.getIdUsluge();

		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			statement.executeUpdate(query);
			System.out.println("Table services updated successfully!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Delete object from table Usluge
	public void obrisiUsluge(int idUsluge) throws SQLException {
		String query = "DELETE FROM services where idUsluge=" + idUsluge;

		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			statement.executeUpdate(query);
			System.out.println("Table services updated successfully!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Main metodu smo prebacili u ManagementInterface
	// Glavna metoda(za provjeru)
	public static void main(String[] args) throws SQLException {
		// samo proba da li radi
		ConnectDB db = new ConnectDB();
		db.createDB();
		db.createTableRooms();
		db.createTableUsers();
		db.createTableServices();

		ArrayList<Room> list = new ArrayList<>();
		list.add(new Room(1, "1-bed", 20, false));
		list.add(new Room(2, "1-bed", 20, false));
		list.add(new Room(3, "1-bed", 20, false));
		list.add(new Room(4, "1-bed", 20, false));
		list.add(new Room(5, "2-bed", 30, false));
		list.add(new Room(6, "2-bed", 30, false));
		list.add(new Room(7, "2-bed", 30, false));
		list.add(new Room(8, "2-bed", 30, false));
		list.add(new Room(9, "apartment", 40, false));
		list.add(new Room(10, "apartment", 40, false));
		list.add(new Room(11, "apartment", 40, false));
		list.add(new Room(12, "apartment", 40, false));

		for (int i = 0; i < 12; i++) {
			db.updateRoom(list.get(i));
		}

		System.out.println("Pozdrav");
	}

}