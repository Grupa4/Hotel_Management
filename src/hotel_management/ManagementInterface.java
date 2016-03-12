package hotel_management;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManagementInterface {
	
	//No-args konstruktor
	public ManagementInterface() {
		super();
	}
	
	//Metoda menia za admina ///////////////////////////////////////
	public void meniZaAdmina() throws SQLException {
		java.util.Scanner unos2 = new java.util.Scanner(System.in);

		System.out.println("Pozdrav");
		
		while(true){
		// Ponuda opcija za ADMINA
		// Na osnovu ovih opcija mozemo sada smisljati objekte, metode i kako da
		// rade

		System.out.println("1 - provjeri korisnike"); // Ispisuje listu (tabelu)
														// nudi pretrazivanje po
														// JMBG, imenu i
														// prezimenu
		System.out.println("2 - registruj korisnika"); // Kreira objekat User i
														// proslijedi ga u bazu
														// podataka Users
		System.out.println("3 - EDIT korisnika (CheckIN)"); // Bira korisnika iz baze,
													// nudi promjenu podataka i
													// vrsi update u bazi
													// podataka
		System.out.println("4 - provjeri sobe"); // Ispisuje listu (tabelu) soba
		System.out.println("5 - rezervisi sobu"); // Rezervise i salje u bazu
		System.out.println("6 - odjavi sobu"); // Odjavljuje i salje u bazu
		System.out.println("7 - provjeri usluge");// Ispisuje listu usluga
		System.out.println("8 - dodaj usluge");// dodaje usluge hotela u bazu
		System.out.println("9 - brisi usluge");// Brise usluge hotela u bazi
		System.out.println("10 - EDIT usluge ");// mijenja karakteristike usluga
												// u bazi
		System.out.println("0 - IZLAZ");

		// Objekat za unos i provjeru unosa
		Unos unos = new Unos();
		int izbor = unos.provjeraInt();

		if (izbor == 1) {
			// Provjera korisnika
			System.out.println("1 - ispisi sve korisnike");
			System.out.println("2 - pretrazi korisnike");
			System.out.println("0 - nazad");
			// Treba ograniciti unos od 0 do 2
			int opcija = unos.provjeraInt();

			if (opcija == 1) {
				UserDaoConcrete user = new UserDaoConcrete();
				System.out.println(user.getUsers());
			} else if (opcija == 2) {
				UserDaoConcrete user = new UserDaoConcrete();
				user.pretraziUsers();
			}
		} else if (izbor == 2) {
			// Dodavanje korisnika
			UserDaoConcrete user = new UserDaoConcrete();
			user.dodajUser();
		} else if (izbor == 3) {
			// Update korisnika
			UserDaoConcrete user = new UserDaoConcrete();
			user.updateUser();
		} else if (izbor == 4) {
			// Provjera soba

			System.out.println("1 - lista svih soba");
			System.out.println("2 - lista praznih soba");
			System.out.println("0 - nazad");
			int opcija = unos.provjeraInt();

			RoomDaoConcrete soba = new RoomDaoConcrete();

			if (opcija == 1) {
				System.out.println(soba.getRooms());
			} else if (opcija == 2) {
				System.out.println(soba.getFreeRooms());
			}

		} else if (izbor == 5) {
			// Rezervisanje sobe
			RoomDaoConcrete soba = new RoomDaoConcrete();

			System.out.println("Unesite broj sobe");
			int brojSobe = unos.provjeraInt();
			soba.occupyRoom(brojSobe);

		} else if (izbor == 6) {
			// Odjava sobe
			RoomDaoConcrete soba = new RoomDaoConcrete();

			System.out.println("Unesite broj sobe");
			int brojSobe = unos.provjeraInt();
			soba.vacateRoom(brojSobe);

		} else if (izbor == 7) {
			// Provjera usluga koje nudi hotel
			UslugeDaoConcrete usluga = new UslugeDaoConcrete();
			ispisiUsluge(usluga.getUsluge());
		} else if (izbor == 8) {
			// Dodavanje usluga koje nudi hotel
			UslugeDaoConcrete usluga = new UslugeDaoConcrete();
			usluga.dodajUsluge();
		} else if (izbor == 9) {
			// Brisanje usluga koje nudi hotel
			UslugeDaoConcrete usluga = new UslugeDaoConcrete();
			usluga.obrisiUsluge();
		} else if (izbor == 10) {
			// Uredjvanje usluga
			UslugeDaoConcrete usluga = new UslugeDaoConcrete();
			usluga.updateUsluge();
		} else if (izbor == 0) {
			break;
		}

		System.out.println("\nENTER - nazad na glavni meni");
		unos2.nextLine();

		}
	}// Kraj metode
	
	/*
	 * Metode za LIJEP ispis arraylista
	 */
	
	//Metoda ispis usluga /////////////////////////
	public void ispisiUsluge(ArrayList<Usluge> lista){
		if (lista.size()==0) {
			System.out.println("Lista je prazna");
		}else{
		//Ispis naziva kolona
		System.out.printf("%9s","idUsluge ");
		System.out.printf("%-22s"," Naziv Usluge");
		System.out.printf("%-15s","Cijena usluge");
		System.out.println();
		//Ispis pojedinih clanova
		for (int i = 0; i < lista.size(); i++) {
			System.out.printf("%9s",lista.get(i).getIdUsluge()+"  ");
			System.out.printf("%-22s","  "+lista.get(i).getNazivUsluge());
			System.out.printf("%-15.2f",lista.get(i).getCijenaUsluge());
			System.out.println(); //Novi red
		}
		}//Kraj metode
	}
}

