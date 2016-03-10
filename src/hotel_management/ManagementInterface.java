package hotel_management;

import java.sql.SQLException;

public class ManagementInterface {

	public static void main(String[] args) throws SQLException {
		//Kreiramo ConnectDB objekat
		ConnectDB db = new ConnectDB();
		db.createDB();
		db.createTableRooms();
		db.createTableUsers();
		db.createTableServices();
		
		System.out.println("Pozdrav");
		
		//Ponuda opcija za ADMINA
		//Na osnovu ovih opcija mozemo sada smisljati objekte, metode i kako da rade
		
		System.out.println("1 - provjeri korisnike"); //Ispisuje listu (tabelu) nudi pretrazivanje po JMBG, imenu i prezimenu
		System.out.println("2 - registruj korisnika"); //Kreira objekat User i proslijedi ga u bazu podataka Users
		System.out.println("3 - EDIT korisnika"); //Bira korisnika iz baze, nudi promjenu podataka i vrsi update u bazi podataka
		System.out.println("4 - provjeri sobe"); //Ispisuje listu (tabelu) soba
		System.out.println("5 - rezervisi sobu"); //Rezervise i salje u bazu
		System.out.println("6 - odjavi sobu"); //Odjavljuje i salje u bazu
		System.out.println("7 - provjeri usluge");//Ispisuje listu usluga
		System.out.println("8 - dodaj usluge");//dodaje usluge hotela u bazu
		System.out.println("9 - brisi usluge");//Brise usluge hotela u bazi
		System.out.println("10 - EDIT usluge ");//mijenja karakteristike usluga u bazi
		System.out.println("0 - IZLAZ");
		
		//Objekat za unos i provjeru unosa
		Unos unos=new Unos();
		int izbor=unos.provjeraInt();
		
		if(izbor==1){
			//ovdje trebamo ubaciti nase metode, objekte ili sta vec treba
		}else if(izbor==2){
			
		}else if(izbor==3){
			
		}else if(izbor==4){
			
		}else if(izbor==5){
			
		}else if(izbor==6){
			
		}else if(izbor==7){
			
		}else if(izbor==8){
			
		}else if(izbor==9){
			
		}else if(izbor==10){
			
		}else if(izbor==0){
			
		}
		
	}
}
