package hotel_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserDaoConcrete implements UserDao {

	public void updateUser(User user) throws SQLException {
		// metoda sa kojom mozemo da mijejammo ime prezme pol godine sobu i tip
		// sobe kao i usluge koje nas gost zeli u hotelu
		// services moramo dodavati kao text u bazu podataka, na slijedeci nacin
		/*
		String servicesString = "";
		if (!gost.getServices().get(0).getNazivUsluge().equals("")) {
		for (int i = 0; i < gost.getServices().size(); i++) {
			// Uzmemo sve podatke
			int idUsluge = gost.getServices().get(i).getIdUsluge();
			String nazivUsluge = gost.getServices().get(i).getNazivUsluge();
			double cijenaUsluge = gost.getServices().get(i).getCijenaUsluge();

			// dodamo u jedan string
			servicesString += idUsluge + ", " + nazivUsluge + ", "
					+ cijenaUsluge + ", \n ";
		}
		}
		*/
		// Ovako spremljen string se lakse cita kada nam budu trebale usluge

		String query = "UPDATE users set name='" + user.getName()
				+ "', surname='" + user.getSurname() + "', gender='"
				+ user.getGender() + "', idCard='" + user.getIdCard()
				+ "', age=" + user.getAge() + ", roomNumber="
				+ user.getRoomNumber() + ", roomType='" + user.getRoomType()
				+ "', checkIn='" + user.getCheckIn() + "', checkInMillis="
				+ user.getcheckInTimeMillis() + ", checkOut='"
				+ user.getCheckOut() + "', checkOutMillis="
				+ user.getCheckOutTimeMillis() + ", userName='"
				+ user.getUserName() + "', password='" + user.getPassword()
				+ "', LoggedIn=" + user.userLogged() + " WHERE idCard LIKE '"
				+ user.getIdCard() + "'";
		
		try (Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dodajUser(User user) throws SQLException {
		// metoda sa kojom dodajemo gosta u nasu tabelu

		// services moramo dodavati kao text u bazu podataka, na slijedeci nacin

		String query = "INSERT INTO users(name, surname, gender, idCard, age, roomNumber, roomType,  checkIn,checkInMillis,checkOut,checkOutMillis, userName, password,LoggedIn) VALUES('"
				+ user.getName()
				+ "', '"
				+ user.getSurname()
				+ "', '"
				+ user.getGender()
				+ "', '"
				+ user.getIdCard()
				+ "', "
				+ user.getAge()
				+ ", "
				+ user.getRoomNumber()
				+ ", '"
				+ user.getRoomType()
				+ "', '"
				+ user.getCheckIn()
				+ "', "
				+ user.getcheckInTimeMillis()
				+ ", '"
				+ user.getCheckOut()
				+ "', "
				+ user.getCheckOutTimeMillis()
				+ ", '"
				+ user.getUserName()
				+ "', '"
				+ user.getPassword()
				+ "',"
				+ user.userLogged() + ")";

		// Usluge ostaju prazne kada tek prijavljujemo korisnika
		try (Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(query);
			System.out.println("Uspjesno dodan gost u tabelu");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void odjaviUser(String idCard) throws SQLException {
		// sa ovom metodom odjavljujemo gosta iz nase baze ali nam njegovi
		// podaci ostaju

		// query da izaberemo gosta na osnovu imena tj koga zelimo odjavimo
		String query = "SELECT FROM user WHERE idCard = " + idCard;
		// skener za citanje sa konzole
		Scanner input = new Scanner(System.in);
		System.out.println("Unesite idCard gosta kojeg zelite odjaviti:");
		// smjetamo unos sa konzole tj koga zelimo da odjavimo
		String idGosta = input.next();
		// drugi query koji ce da nam promijeni u tabeli stanje gosta tj da ga
		// odjavi
		String query1 = "UPDATE users set userLogged=false WHERE idCard = "
				+ idGosta;
		ResultSet rs = null;
		// konekcija sa bazom
		try (Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			// updajte query1
			statement.executeUpdate(query1);
			rs = statement.executeQuery(query);
			rs.next();
			// ispis
			System.out.println("Vas gost " + rs.getString("idCard")
					+ " uspjesno je odjavljen iz hotela");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<User> pretraziUsersName(String name) throws SQLException {
		// trazimo goste u bazi na osnovu imena
		// Iskljucit cemo ga zasad posto proslijedjujemo podatke kroz metodu
		// Scanner input = new Scanner(System.in);
		// ispis
		// System.out.println("Unesite ime kojeg gosta trazite");
		// smjestamo koje ime trazimo
		// String gostIme = input.next();
		// query
		ArrayList<User> gosti = new ArrayList<>();
		String query = "SELECT * from users WHERE name like '" + name + "'";
		ResultSet rs = null;
		try (Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(query);
			while (rs.next()) {

				User gost = new User();
				gost.setName(rs.getString("name"));
				gost.setSurname(rs.getString("surname"));
				gost.setGender(rs.getString("gender").charAt(0));
				gost.setIdCard(rs.getString("idCard"));
				gost.setAge(rs.getInt("age"));
				gost.setRoomNumber(rs.getInt("roomNumber"));
				gost.setRoomType(rs.getString("roomType"));
				gost.copyCheckIn(rs.getString("checkIn"));
				gost.copyCheckInMillis(rs.getLong("checkInMillis"));
				gost.copyCheckOut(rs.getString("checkOut"));
				gost.copyCheckOutMillis(rs.getLong("checkOutMillis"));
				gost.setUserName(rs.getString("userName"));
				gost.setPassword(rs.getString("password"));
				// gost.setUserAktivan(rs.getBoolean("userAktivan"));
				gost.setUserLogged(rs.getBoolean("userLogged"));
				gosti.add(gost);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gosti;
		/*
		try (Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(query);
			rs.next();
			// ispis
			System.out.println("Podaci o vasem trazenom gostu su :"
					+ rs.getString("name") + rs.getString("surname")
					+ rs.getInt("age") + rs.getString("idCard")
					+ rs.getInt("roomNumber") + rs.getInt("roomType"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}

	@Override
	public ArrayList<User> pretraziUsersIdCard(String idCard) throws SQLException {
		// trazimo goste u bazi na osnovu imena
		// Iskljucit cemo ga zasad posto proslijedjujemo podatke kroz metodu
		// Scanner input = new Scanner(System.in);
		// ispis
		// System.out.println("Unesite maticni broj gosta kojeg trazite");
		// smjestamo koje ime trazimo
		// String idCard = input.next();
		// query
		ArrayList<User> gosti = new ArrayList<>();
		String query = "SELECT * from users WHERE idCard LIKE '"+idCard+"'";
		ResultSet rs = null;
		try (Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(query);
			while (rs.next()) {

				User gost = new User();
				gost.setName(rs.getString("name"));
				gost.setSurname(rs.getString("surname"));
				gost.setGender(rs.getString("gender").charAt(0));
				gost.setIdCard(rs.getString("idCard"));
				gost.setAge(rs.getInt("age"));
				gost.setRoomNumber(rs.getInt("roomNumber"));
				gost.setRoomType(rs.getString("roomType"));
				gost.copyCheckIn(rs.getString("checkIn"));
				gost.copyCheckInMillis(rs.getLong("checkInMillis"));
				gost.copyCheckOut(rs.getString("checkOut"));
				gost.copyCheckOutMillis(rs.getLong("checkOutMillis"));
				gost.setUserName(rs.getString("userName"));
				gost.setPassword(rs.getString("password"));
				// gost.setUserAktivan(rs.getBoolean("userAktivan"));
				gost.setUserLogged(rs.getBoolean("userLogged"));
				gosti.add(gost);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return gosti;
		/*
		try (Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(query);
			rs.next();
			// ispis

			System.out.println("Podaci o vasem trazenom gostu su :"
					+ rs.getString("name") + rs.getString("surname")
					+ rs.getInt("age") + rs.getString("idCard")
					+ rs.getInt("roomNumber") + rs.getInt("roomType"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}

	// Metoda za provjeru passworda i username i biranje idCard korisnika
	@Override
	public String provjeriKorisnika(String username, String password) throws SQLException {
		
		String query = "SELECT idCard, userName, password FROM users";
		 
		try(Connection connection = ConnectDB.getConnected();
			Statement statement = connection.createStatement();){
			
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				String id = resultSet.getString(1);
				String name = resultSet.getString(2);
				String pass = resultSet.getString(3);
				if(name.equals(username) && pass.equals(password))
					return id;
			}
		}
		return null;
	}

	@Override
	public ArrayList<User> getUsers() throws SQLException {
		// dobijamo listu svih objekata tj gostiju koji se nalaze u nasoj tabeli
		ArrayList<User> gosti = new ArrayList<>();
		String query = "SELECT * from users";
		ResultSet rs = null;
		try (Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(query);
			while (rs.next()) {
				
				User gost = new User();
				gost.setName(rs.getString("name"));
				gost.setSurname(rs.getString("surname"));
				gost.setGender(rs.getString("gender").charAt(0));
				gost.setIdCard(rs.getString("idCard"));
				gost.setAge(rs.getInt("age"));
				gost.setRoomNumber(rs.getInt("roomNumber"));
				gost.setRoomType(rs.getString("roomType"));
				gost.copyCheckIn(rs.getString("checkIn"));
				gost.copyCheckInMillis(rs.getLong("checkInMillis"));
				gost.copyCheckOut(rs.getString("checkOut"));
				gost.copyCheckOutMillis(rs.getLong("checkOutMillis"));
				gost.setUserName(rs.getString("userName"));
				gost.setPassword(rs.getString("password"));
				// gost.setUserAktivan(rs.getBoolean("userAktivan"));
				gost.setUserLogged(rs.getBoolean("userLogged"));
				gosti.add(gost);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gosti;
	}

	@Override
	public void checkInUser(String idKorisnika) throws SQLException {
		UserDaoConcrete korisnici=new UserDaoConcrete();
		ArrayList<User>listaKorisnika=korisnici.getUsers();
		User user=null;
		
		for (int i = 0; i < listaKorisnika.size(); i++) {
			if (listaKorisnika.get(i).getIdCard().equals(idKorisnika)) {
				user=listaKorisnika.get(i);
				break;
			}
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String currentDate = dateFormat.format(date);

		user.setCheckIn(currentDate);

		updateUser(user);
	}

	@Override
	public void checkOutUser(String idKorisnika) throws SQLException {
		UserDaoConcrete korisnici=new UserDaoConcrete();
		ArrayList<User>listaKorisnika=korisnici.getUsers();
		User user=null;
		
		for (int i = 0; i < listaKorisnika.size(); i++) {
			if (listaKorisnika.get(i).getIdCard().equals(idKorisnika)) {
				user=listaKorisnika.get(i);
				break;
			}
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String currentDate = dateFormat.format(date);

		user.setCheckOut(currentDate);

		updateUser(user);
	}

	// jos nije gotova
	@Override
	public void promjenaSobe(User gost, int brojSobe) throws SQLException {
		// TODO Auto-generated method stub
		String query = " UPDATE user SET brojSobe= '" + brojSobe
				+ "' WHERE userName = " + gost.getUserName();

	}

	// Metoda za citanje usluga iz tabele i kreiranje objekta usluge
	public ArrayList<Usluge> kreirajListuUsluge(String idCard) {

		ArrayList<Usluge> services = new ArrayList<Usluge>();

		String query = "SELECT services from users where idCard like '"
				+ idCard + "'";
		ResultSet rs = null;
		String servicesString = "";
		try (Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(query);

			while (rs.next()) {
				// Uzmemo prvi podatak
				servicesString = rs.getString("services");
				// Ocitamo objekat i dodamo ga u listu usluga objekata
				services.add(ocitajUslugu(servicesString));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return services;

	}

	// Uzimanje clanova do praznog zareza iz baze
	public Usluge ocitajUslugu(String str) {
		int i = 0;
		String idBroj = "0";
		String naziv = "";
		String cijena = "0";
		char karakter = str.charAt(i);

		do {
			idBroj += karakter;
			i++;
			karakter = str.charAt(i);
		} while (karakter != ',');
		i++; // Da preskocimo zarez
		karakter = str.charAt(i);
		do {
			naziv += karakter;
			i++;
			karakter = str.charAt(i);
		} while (karakter != ',');
		i++; // preskocimo zarez
		karakter = str.charAt(i);
		do {
			cijena += karakter;
			i++;
			karakter = str.charAt(i);
		} while (karakter != ',');
		
		idBroj = idBroj.trim();
		naziv = naziv.trim();
		cijena = cijena.trim();

		int idUsluge = Integer.parseInt(idBroj);
		String nazivUsluge = naziv;
		double cijenaUsluge = Double.parseDouble(cijena);

		return (new Usluge(idUsluge, nazivUsluge, cijenaUsluge));

	}// Kraj metode

}