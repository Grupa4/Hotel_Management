package hotel_management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Konekcija {

	// objekat konekcije
	private static Konekcija instanca = null;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String CONN_STRING = "jdbc:mysql://localhost/";
	private static final String DB = "hotel_management";

	private static Connection connection = null;

	// konstruktor konekcije
	public Konekcija() {

	}

	// metoda koja vraca objekat ove klase
	public static Konekcija vratiInstancu() {
		// ako je objekat prazan
		if (instanca == null) {
			// napravi novi objekat ove klase
			instanca = new Konekcija();
		}
		// vrati objekat klase
		return instanca;
	}

	// metoda koja vraca da li smo konektovani na bazu podataka
	public Connection getConnection() {
		if (connection == null) {
			if (openConnection()) {
				System.out.println("Connection opend!");
				return connection;
			} else {
				return null;
			}
		}
		return connection;
	}

	// metoda sa kojom se konektujemo na bazu
	private boolean openConnection() {
		try {
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			System.out.println("Connected!");
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	// metoda sa kojom zatvaramo konekciju
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Connection closed");
			connection = null;
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
