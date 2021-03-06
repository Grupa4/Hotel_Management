package hotel_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class User {

	// Data fields
	private String name;
	private String surname;
	private char gender;
	private String idCard;
	private int age;
	private int roomNumber;
	private String roomType;
	private long checkInTimeMillis;
	private String checkIn;
	private String userName;
	private String password;
	private ArrayList<Usluge> services;
	private boolean userAktivan;
	private boolean userLogged;
	private String checkOut;
	private long checkOutTimeMillis;

	// No args Constructor
	public User() {
		setUserAktivan(true);

	}

	// Constructor with specified arguments
	public User(String name, String surname, char gender, String idCard, int age,
			String userName, String password) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.idCard = idCard;
		this.age = age;
		this.roomNumber = 0;
		this.roomType = "";
		this.userName = userName;
		this.password = password;
		this.checkIn="";
		this.checkInTimeMillis=0;
		this.checkOut="";
		this.checkOutTimeMillis=0;
		setUserLogged(false);
		setUserAktivan(true);
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	public void synchronizeRoomTypeAndNumber() throws SQLException{
		int number=getRoomNumber();
		
		if(number!=0){
		String query = "SELECT type FROM rooms WHERE number="+number;
		
		try(Connection connection = ConnectDB.getConnected();
			Statement statement = connection.createStatement();) {
			
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();
			
			String type=resultSet.getString(1);
			setRoomType(type);
			
		}catch (SQLException e){
			System.out.println(e.toString());
		}
		}else {
			setRoomType("");
		}
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		//prilikom checkiranja memorise vrijeme u milisekundama
		this.checkInTimeMillis=System.currentTimeMillis();
		this.checkIn = checkIn;
	}
	
	public void copyCheckIn(String checkIn) {
		this.checkIn=checkIn;
	}
	
	public long getcheckInTimeMillis(){
		return this.checkInTimeMillis;
	}
	
	public void copyCheckInMillis(long checkInMillis) {
		this.checkInTimeMillis=checkInMillis;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public final boolean userAktivan() {
		return userAktivan;
	}

	public final void setUserAktivan(boolean userAktivan) {
		this.userAktivan = userAktivan;
	}
	
	//Dodavanje usluge korisniku
	public void setUslugu(int idUsluge){
		String query = "SELECT * FROM services where idCard like '"+idUsluge+"'";

		try (Statement statement = ConnectDB.getConnected().createStatement();) {

			ResultSet resultSet = statement.executeQuery(query);
			int idUs=resultSet.getInt(1);
			String nazivUsluge=resultSet.getString(2);
			double cijenaUsluge=resultSet.getDouble(3);
			
			this.services.add(new Usluge(idUs, nazivUsluge, cijenaUsluge));
			
			System.out.println("Usluga uspjesno dodana!");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Brisanje usluge korisnika
	public void obrisiUslugu(int indeksUsluge){
		try {
			this.services.remove(indeksUsluge);
			System.out.println("Usluga "+this.services.get(indeksUsluge)+" obrisana!");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Indeks ne postoji!");
		}
	}
	
	public void setServices(ArrayList<Usluge> lista){
		this.services.addAll(lista);
	}

	public ArrayList<Usluge> getServices() {
		return services;
	}

	public boolean userLogged() {
		return userLogged;
	}

	public void setUserLogged(boolean userLogged) {
		this.userLogged = userLogged;
	}

	public void setServices(int idUsluge, String nazivUsluge, double cijenaUsluge) {
		this.services.add(new Usluge(idUsluge, nazivUsluge, cijenaUsluge));
	}

	public String getCheckOut() {
		return checkOut;
	}
	
	public void copyCheckOut(String checkOut) {
		this.checkOut=checkOut;
	}
	
	public long getCheckOutTimeMillis(){
		return this.checkOutTimeMillis;
	}
	
	public void copyCheckOutMillis(long checkOutMillis) {
		this.checkOutTimeMillis=checkOutMillis;
	}

	public void setCheckOut(String checkOut) {
		//prilikom odjave memorise vrijeme u milisekundama
		this.checkOutTimeMillis=System.currentTimeMillis();
		this.checkOut = checkOut;
	}
	
	// ovdje nam treba metoda za ispis mislim da bi se moglo jos nesto dodat
	public String toString() {
		return "Name " + name + " surname " + surname + " userName " + userName;
	}
	
	//Metoda za obracun svih cijena//////////////////////////////
	public double obracunajBoravak(String idKorisnika){
		
		long razlikaMillis=(this.checkOutTimeMillis-this.checkInTimeMillis);
		int brojDana=(int)(razlikaMillis/(1000*60*60*24));
		
		//Trebamo smisliti kako cijene soba odredjivati, npr.
		double cijenaSobe=getCijenaSobe();
		
		//Obracun svih dodatnih usluga
		double ukupnaCijenaUsluga=0;
		for (int i = 0; i < services.size(); i++) {
			ukupnaCijenaUsluga += services.get(i).getCijenaUsluge();
		}
		
		double ukupanRacun=(cijenaSobe+ukupnaCijenaUsluga)*brojDana;
		
		return ukupanRacun;
	}
	
	//Metoda za uzimanje cijene sobe iz baze
	public int getCijenaSobe(){
		int number=getRoomNumber();
		int cijena=0;
		
		if (number!=0) {
		String query = "SELECT dayPrice FROM rooms WHERE number="+number;
		
		try(Connection connection = ConnectDB.getConnected();
			Statement statement = connection.createStatement();) {
			
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();
			
			cijena=resultSet.getInt(1);
		}catch (SQLException e){
			System.out.println(e.toString());
		}
		return cijena;
		}else {
			return cijena;
		}
	}//Kraj metode
}
