package hotel_management;

import java.sql.SQLException;

public class MainMetoda {

	public static void main(String[] args) throws SQLException {
		// Kreiramo ConnectDB objekat
		ConnectDB db = new ConnectDB();
		db.createDB();
		db.createTableRooms();
		db.createTableUsers();
		db.createTableServices();

		System.out.println("\nHOTEL SISTEM - DOBRODOSLI! \n");
		System.out.println("Unesite username:");
		System.out.println("Unesite password:");
		// Sada nekako sistem javi je li admin ili obicni gost pa na osnovu toga
		// udjemo u odgovarajuci meni
		System.out.println(); //Prazan red
		// Ulazimo u meni administratora
		ManagementInterface admin = new ManagementInterface();
		admin.meniZaAdmina();

	}

}
