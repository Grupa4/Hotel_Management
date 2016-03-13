package hotel_management;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainMetoda {

	public static void main(String[] args) throws Exception {

		java.util.Scanner unos = new java.util.Scanner(System.in);

		// Kreiramo ConnectDB objekat
		ConnectDB db = new ConnectDB();
		db.createDB();
		db.createTableRooms();
		db.createTableUsers();
		db.createTableServices();
		CreateRooms cr = new CreateRooms();

		System.out.println("\nHOTEL SISTEM - DOBRODOSLI! \n");
		String username;
		String password;
		while (true) {
			System.out.println("Unesite username:");
			username = unos.next();
			System.out.println("Unesite password:");
			password = unos.next();
			ManagementInterface admin = new ManagementInterface();
			UserDao user = new UserDaoConcrete();

			if (username.equals(ConnectDB.username) && password.equals(ConnectDB.password)) {
				System.out.println("Admin!");
				admin.meniZaAdmina();
			} else if (user.provjeriKorisnika(username, password) != null) {
				String idKorisnika = user.provjeriKorisnika(username, password);
				System.out.println(); // Prazan red
				GuestInterface gost = new GuestInterface(idKorisnika);
				gost.meniKorisnika();
			} else {
				System.out.println("Pogresan unos! Pokusajte ponovo!");
				continue;
			}
		}
	}
}
