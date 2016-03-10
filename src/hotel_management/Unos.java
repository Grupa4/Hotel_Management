package hotel_management;

public class Unos {
	
	// Metoda za provjeru Int unosa /////////////////////////////////////
		public int provjeraInt() {
			java.util.Scanner unos = new java.util.Scanner(System.in);
			int unosKorisnika = 0;
			boolean beskonacanCiklus = true;
			// Petlja ponavlja unos dok ne bude tacan
			while (beskonacanCiklus) {
				try {
					unosKorisnika = unos.nextInt();
					beskonacanCiklus = false; // Izlaz iz petlje
					unos.nextLine();
				} catch (Exception e) {
					System.out.println("Pogresan unos! Unesite ponovo");
					unos.nextLine();
				}
			}
			return unosKorisnika;
		}// Kraj metode

}
