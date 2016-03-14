package hotel_management;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class ManagementInterface {

	// No-args konstruktor
	public ManagementInterface() {
		super();
	}

	// Metoda menia za admina ///////////////////////////////////////
	public void meniZaAdmina() throws SQLException {
		java.util.Scanner unos2 = new java.util.Scanner(System.in);

		System.out.println("Pozdrav");

		while (true) {
			
			System.out.println("1 - provjeri korisnike"); // Ispisuje listu
															// (tabelu)
															// nudi
															// pretrazivanje po
															// JMBG, imenu i
															// prezimenu
			System.out.println("2 - registruj korisnika"); // Kreira objekat
															// User i
															// proslijedi ga u
															// bazu
															// podataka Users
			System.out.println("3 - EDIT korisnika, CheckIN, dodijeli sobu..."); // Bira
																				// korisnika
																				// iz
																				// baze,
			// nudi promjenu podataka i
			// vrsi update u bazi
			// podataka
			System.out.println("4 - provjeri sobe"); // Ispisuje listu (tabelu)
														// soba
			System.out.println("5 - provjeri usluge");// Ispisuje listu usluga
			System.out.println("6 - dodaj usluge");// dodaje usluge hotela u
													// bazu
			System.out.println("7 - brisi usluge");// Brise usluge hotela u bazi
			System.out.println("8 - EDIT usluge ");// mijenja karakteristike
													// usluga
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
					ispisiKorisnike(user.getUsers());
				} else if (opcija == 2) {
					UserDaoConcrete user = new UserDaoConcrete();
					System.out
							.println("Unesite ime ili maticni broj korisnika:");
					String podatak = unos2.next();
					unos2.nextLine();

					if (Character.isDigit(podatak.charAt(0))) {
						ArrayList<User>lista=user.pretraziUsersIdCard(podatak);
						ispisiKorisnike(lista);
					} else if (Character.isLetter(podatak.charAt(0))) {
						ArrayList<User>lista=user.pretraziUsersName(podatak);
						ispisiKorisnike(lista);
					} else {
						System.out.println("Pogresan unos! \n");
					}

				}
			} else if (izbor == 2) {
				// Dodavanje korisnika
				UserDaoConcrete user = new UserDaoConcrete();
				User noviUser = napraviProfil();
				System.out.println("Profil gotov");
				user.dodajUser(noviUser);

			} else if (izbor == 3) {
				// Update korisnika
				System.out.println("1 - checkIn");
				System.out.println("2 - checkOut");
				System.out.println("3 - rezervisi sobu");
				System.out.println("4 - odjavi sobu");
				System.out.println("5 - EDIT korisnika");
				System.out.println("6 - OBRISI KORISNIKA");
				System.out.println("0 - izlaz");
				int opcija = unos2.nextInt();
				unos2.nextLine();

				UserDaoConcrete user = new UserDaoConcrete();
				String idCard="";
				
				if(opcija>=1||opcija<=6){
				System.out.println("Unesite maticni broj korisnika:");
				idCard = unos2.next();
				unos2.nextLine();
				}
				ArrayList<User> listaKorisnika = user.getUsers();
				User korisnik = null;
				boolean korisnikPostoji = false;
				// Provjera broja
				for (int i = 0; i < listaKorisnika.size(); i++) {
					if (listaKorisnika.get(i).getIdCard().equals(idCard)) {
						korisnik = listaKorisnika.get(i);
						korisnikPostoji = true;
						break;
					}
				}

				if (!korisnikPostoji) {
					System.out.println("Trazeni broj ne postoji!");
				} else {

					if (opcija == 1) {
						//CheckIn
						user.checkInUser(idCard);
						ArrayList<User>lista=user.pretraziUsersIdCard(idCard);
						ispisiKorisnike(lista);
					} else if (opcija == 2) {
						//CheckOut
						user.checkOutUser(idCard);
						ArrayList<User>lista=user.pretraziUsersIdCard(idCard);
						ispisiKorisnike(lista);
					} else if (opcija == 3) {
						//Rezervisi sobu
						System.out.println("Unesite broj sobe");
						int roomNumber = unos2.nextInt();
						unos2.nextLine();

						RoomDaoConcrete slobodnaSoba = new RoomDaoConcrete();
						if (slobodnaSoba.isFreeRoom(roomNumber)) {
							slobodnaSoba.occupyRoom(roomNumber);
							korisnik.setRoomNumber(roomNumber);
							korisnik.synchronizeRoomTypeAndNumber();
							user.updateUser(korisnik);
							ArrayList<User>lista=user.pretraziUsersIdCard(idCard);
							ispisiKorisnike(lista);
						} else {
							System.out.println("Soba je zauzeta");
						}
					} else if (opcija == 4) {
						//Odjavi sobu
						int roomNumber = korisnik.getRoomNumber();

						RoomDaoConcrete slobodnaSoba = new RoomDaoConcrete();
						slobodnaSoba.vacateRoom(roomNumber);
						korisnik.setRoomNumber(0);
						korisnik.synchronizeRoomTypeAndNumber();
						user.updateUser(korisnik);
					} else if (opcija == 5) {
						//Update informacije
						user.updateUser(updateProfil(idCard));
					}else if(opcija==6){
						//Obrisi korisnika
						user.obrisiUser(idCard);
					}

				}
			} else if (izbor == 4) {
				// Provjera soba

				System.out.println("1 - lista svih soba");
				System.out.println("2 - lista praznih soba");
				System.out.println("0 - nazad");
				int opcija = unos.provjeraInt();

				RoomDaoConcrete soba = new RoomDaoConcrete();

				if (opcija == 1) {
					ispisiSobe(soba.getRooms());
				} else if (opcija == 2) {
					ispisiSobe(soba.getFreeRooms());
				}

			} else if (izbor == 5) {
				// Provjera usluga koje nudi hotel
				UslugeDaoConcrete usluga = new UslugeDaoConcrete();
				ispisiUsluge(usluga.getUsluge());
			} else if (izbor == 6) {
				// Dodavanje usluga koje nudi hotel
				UslugeDaoConcrete usluga = new UslugeDaoConcrete();
				usluga.dodajUsluge();
			} else if (izbor == 7) {
				// Brisanje usluga koje nudi hotel
				UslugeDaoConcrete usluga = new UslugeDaoConcrete();
				usluga.obrisiUsluge();
			} else if (izbor == 8) {
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

	// Metoda ispis usluga /////////////////////////
	public void ispisiUsluge(ArrayList<Usluge> lista) {
		if (lista.size() == 0) {
			System.out.println("Lista je prazna");
		} else {
			// Ispis naziva kolona
			System.out.printf("%9s", "idUsluge ");
			System.out.printf("%22s", " ----Naziv Usluge---- ");
			System.out.printf("%15s", " Cijena usluge ");
			System.out.println();
			// Ispis pojedinih clanova
			for (int i = 0; i < lista.size(); i++) {
				System.out.printf("%9s", lista.get(i).getIdUsluge() + " |");
				System.out.printf("%22s", "  " + lista.get(i).getNazivUsluge()
						+ " |");
				System.out
						.printf("%15s", lista.get(i).getCijenaUsluge() + " |");
				System.out.println(); // Novi red
			}
		}
	}// Kraj metode

	// Metoda ispis korisnika /////////////////////////
	public void ispisiKorisnike(ArrayList<User> lista) {
		if (lista.size() == 0) {
			System.out.println("Lista je prazna");
		} else {
			// Ispis naziva kolona
			System.out.printf("%15s", "---name---   ");
			System.out.printf("%15s", "--surname--   ");
			System.out.printf("%8s", "-gender-");
			System.out.printf("%20s", "  -----idCard------ ");
			System.out.printf("%8s", " -age-  ");
			System.out.printf("%14s", " roomNumber   ");
			System.out.printf("%12s", " roomType   ");
			System.out.printf("%12s", " checkIn  -");
			System.out.printf("%12s", " checkOut -");
			System.out.printf("%20s", "  ---username---    ");
			System.out.printf("%12s", "- password -");
			System.out.printf("%12s", " userLogged ");
			System.out.println();
			// Ispis pojedinih clanova
			for (int i = 0; i < lista.size(); i++) {
				System.out.printf("%15s", lista.get(i).getName() + " |");
				System.out.printf("%15s", lista.get(i).getSurname() + " |");
				System.out.printf("%8s", lista.get(i).getGender() + " |");
				System.out.printf("%20s", lista.get(i).getIdCard() + " |");
				System.out.printf("%8s", lista.get(i).getAge() + " |");
				System.out.printf("%14s", lista.get(i).getRoomNumber() + " |");
				System.out.printf("%12s", lista.get(i).getRoomType() + " |");
				System.out.printf("%12s", lista.get(i).getCheckIn() + " |");
				System.out.printf("%12s", lista.get(i).getCheckOut() + " |");
				System.out.printf("%20s", lista.get(i).getUserName() + " |");
				System.out.printf("%12s", lista.get(i).getPassword() + " |");
				System.out.printf("%12s", lista.get(i).userLogged() + " |");

				System.out.println(); // Novi red
			}
		}
	}// Kraj metode

	// Metoda ispis soba /////////////////////////
	public void ispisiSobe(ArrayList<Room> lista) {
		if (lista.size() == 0) {
			System.out.println("Lista je prazna");
		} else {
			// Ispis naziva kolona
			System.out.printf("%7s", "idSobe ");
			System.out.printf("%15s", "--Naziv Sobe-- ");
			System.out.printf("%12s", "Cijena sobe ");
			System.out.printf("%7s", "-Zauzeto-");

			System.out.println();
			// Ispis pojedinih clanova
			for (int i = 0; i < lista.size(); i++) {
				System.out.printf("%7s", lista.get(i).getNumber() + " |");
				System.out.printf("%15s", lista.get(i).getType() + " |");
				System.out.printf("%12s", lista.get(i).getDayPrice() + " |");
				System.out.printf("%9s", lista.get(i).getOccupied() + " |");
				System.out.println(); // Novi red
			}
		}
	}// Kraj metode

	// Metoda za kreiranje novog objekta korisnika/////////
	public User napraviProfil() {
		java.util.Scanner unos = new java.util.Scanner(System.in);

		System.out.println("Unesite ime korisnika:");
		String name = unos.next();
		System.out.println("Unesite prezime:");
		String surname = unos.next();
		System.out.println("Unesite spol:");
		char gender = unos.next().charAt(0);
		System.out.println("Unesite maticni broj:");
		String idCard = unos.next();
		System.out.println("Unesite godine:");
		int age = unos.nextInt();
		System.out.println("Unesite korisnicko ime:");
		String userName = unos.next();
		System.out.println("Unesite password:");
		String password = unos.next();
		unos.nextLine(); // Da primi enter

		User user = new User(name, surname, gender, idCard, age,
				userName, password);

		return user;
	}// Kraj metode

	// Metoda za kreiranje novog objekta korisnika/////////
	public User updateProfil(String idCard) {
		java.util.Scanner unos = new java.util.Scanner(System.in);

		System.out.println("Unesite ime korisnika:");
		String name = unos.next();
		System.out.println("Unesite prezime:");
		String surname = unos.next();
		System.out.println("Unesite spol:");
		char gender = unos.next().charAt(0);
		System.out.println("Unesite godine:");
		int age = unos.nextInt();
		System.out.println("Unesite korisnicko ime:");
		String userName = unos.next();
		System.out.println("Unesite password:");
		String password = unos.next();
		unos.nextLine(); // Da primi enter

		User user = new User(name, surname, gender, idCard, age,
				 userName, password);

		return user;
	}// Kraj metode
}
