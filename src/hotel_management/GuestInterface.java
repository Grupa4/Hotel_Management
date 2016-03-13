package hotel_management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class GuestInterface {

	private String surname;
	private String gender;
	private long checkInTimeMillis;
	private long checkOutTimeMillis;
	private ArrayList<Usluge> services;

	// NO-args konstruktor
	public GuestInterface() {
		super();
	}

	public GuestInterface(String idKorisnika) {

		String query = "SELECT * FROM users where idCard like '" + idKorisnika + "'";

		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			ResultSet resultSet = statement.executeQuery(query);
			this.surname = resultSet.getString(2);
			this.gender = resultSet.getString(3);
			this.checkInTimeMillis = resultSet.getLong(9);

			// Treba smisliti kako memorisati u tabeli objekat usluga i citati
			// ih poslije

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	// Metoda meni-a korisnika
	public void meniKorisnika(User gost) throws Exception {

		System.out.println("Dobro dosli na nas hotel!");
		Scanner input = new Scanner(System.in);

		try {
			System.out.println("Za stanje koliko ste duzni hotelu ukucajte 1, "
					+ "za ispis usluga i njihove cijene ukucajte 2, "
					+ "za prijavu u sobu ukucajte 3, za promjenu sobe ukucajte 4 " + "i da se odjavite ukucajte 5");
			int izborGosta = input.nextInt();
			if (izborGosta == 1) {
				// ovjde ima neka greska ne znam rijesit
				
				double stanje = gost.obracunajBoravak(gost.getIdCard());
				System.out.println("Vas racun je: " + stanje);
			}

			else if (izborGosta == 2) {
				System.out.println("Unesite id usluge koju zelite:");
				ArrayList<Usluge> listaUsluga = gost.getServices();
				for (Usluge u : listaUsluga) {
					System.out.println("Id usluge: " + u.getIdUsluge() + " naziv usluge: " + u.getNazivUsluge()
							+ " cijena usluge: " + u.getCijenaUsluge());
				}
			} else if (izborGosta == 3) {
				System.out.println("Molimo vas da na recepciji potvrdite svoju rezervaciju:");
				if (!gost.userLogged()) {
					gost.userAktivan();
				}
			} else if (izborGosta == 4) {
				System.out.println("Unesite koju sobu zelite sada:");
				int promjenaSobe = input.nextInt();
				gost.setRoomNumber(promjenaSobe);
			} else if (izborGosta == 5) {
				gost.setUserAktivan(false);
			}
		} catch (Exception e) {
			System.err.println(e);
			input.nextLine();
		}
	}
}