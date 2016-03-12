package hotel_management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GuestInterface {
	
	private String surname;
	private String gender;
	private long checkInTimeMillis;
	private long checkOutTimeMillis;
	private ArrayList<Usluge> services;
	
	//NO-args konstruktor
	public GuestInterface() {
		super();
	}
	
	public GuestInterface(String idKorisnika){
		
		String query = "SELECT * FROM users where idCard like '"+idKorisnika+"'";

		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			ResultSet resultSet = statement.executeQuery(query);
			this.surname=resultSet.getString(2);
			this.gender=resultSet.getString(3);
			this.checkInTimeMillis=resultSet.getLong(9);
			
			//Treba smisliti kako memorisati u tabeli objekat usluga i citati ih poslije
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	//Metoda meni-a korisnika
	public void meniKorisnika(){
		
	}
	
	
	//Metoda za provjeru duga
	public void provjeriDug(){
		
		
	}
	
	
	
}
