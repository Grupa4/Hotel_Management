package hotel_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserDaoConcrete implements UserDao {

	public void updateUser(User gost) throws SQLException {
		// metoda sa kojom mozemo da mijejammo ime prezme pol godine sobu i tip
		// sobe kao i usluge koje nas gost zeli u hotelu
		String query = "UPDATE users SET name='" + gost.getName() + "', surname=" + gost.getSurname() + ", gender="
				+ gost.getGender() + ", age=" + gost.getAge() + ", roomNumber=" + gost.getRoomNumber() + ", roomType="
				+ gost.getRoomNumber() + ", services=" + gost.getServices() + ", WHERE username='" + gost.getName()
				+ "'";
		try (Connection connection = ConnectDB.getConnected(); Statement statement = connection.createStatement();) {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dodajUser(User gost) throws SQLException {
		// metoda sa kojom dodajemo gosta u nasu tabelu
		String query = "INSERT into users(name, surname,gender,idCard,age,roomNumber,roomType,userName, password,services VALUES("
				+ gost.getName() + ", '" + gost.getSurname() + ", '" + gost.getGender() + ", '" + gost.getIdCard()
				+ ", '" + gost.getAge() + ", '" + gost.getRoomNumber() + ", '" + gost.getRoomType() + ", '"
				+ gost.getUserName() + ", '" + gost.getPassword() + ", '" + gost.getServices() + "')";
		try (Connection connection = ConnectDB.getConnected(); Statement statement = connection.createStatement();) {
			statement.executeQuery(query);
			System.out.println("Uspjesno dodan gostu tabelu");
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
		String query1 = "UPDATE users set userLogged WHERE idCard = " + idGosta;
		ResultSet rs = null;
		// konekcija sa bazom
		try (Connection connection = ConnectDB.getConnected(); Statement statement = connection.createStatement();) {
			// updajte query1
			statement.executeUpdate(query1);
			rs = statement.executeQuery(query);
			rs.next();
			// ispis
			System.out.println("Vas gost " + rs.getString("idCard") + " uspjesno je odjavljen iz hotela");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void pretraziUsers(String name) throws SQLException {
		// trazimo goste u bazi na osnovu imena
		Scanner input = new Scanner(System.in);
		// ispis
		System.out.println("Unesite ime kojeg gosta trazite");
		// smjestamo koje ime trazimo
		String gostIme = input.next();
		// query
		String query = "SELECT * from users WHERE name = " + gostIme;
		ResultSet rs = null;
		// konekcija i izvrsavanje querija
		try (Connection connection = ConnectDB.getConnected(); Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(query);
			// ispis
			System.out.println("Podaci o vasem trazenom gostu su :" + rs.getString("name") + rs.getString("surname")
					+ rs.getInt("age") + rs.getString("idCard") + rs.getInt("roomNumber") + rs.getInt("roomType")
					+ rs.getDate("chekIn") + rs.getBoolean("userAtivan") + rs.getDate("checkOut")
					+ rs.getArray("services"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<User> getUsers() throws SQLException {
		// dobijamo listu svih objekata tj gostiju koji se nalaze u nasoj tabeli 
		ArrayList<User> gosti = new ArrayList<>();
		String query = "SELECT * from users";
		ResultSet rs = null;
		try (Connection connection = ConnectDB.getConnected(); Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(query);
			while (rs.next()) {
				User gost = new User();
				gost.setName(rs.getString("name"));
				gost.setSurname(rs.getString("surname"));
				gost.setGender(rs.getString("gender").charAt(0));
				gost.setIdCard(rs.getString("idCard"));
				gost.setAge(rs.getInt("age"));
				gost.setRoomNumber(rs.getInt("roomNumber"));
				gost.setRoomType(rs.getInt("roomType"));
				gost.setCheckIn(rs.getDate("checkIn"));
				gost.setUserName(rs.getString("userName"));
				gost.setPassword(rs.getString("password"));
				// ovdje bi mozda trebale biti usluge koje gost koristi u nasem
				// hotelu ali ne znam kako
				gost.setUserAktivan(rs.getBoolean("userAktivan"));
				gost.setUserLogged(rs.getBoolean("userLogged"));
				gost.setCheckOut(rs.getDate("checkOut"));
				gosti.add(gost);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gosti;
	}

	@Override
	public void checkInUser() throws SQLException {
		
	}

	@Override
	public void promjenaSobe(User gost, int brojSobe) throws SQLException {
		// TODO Auto-generated method stub
		String query = " UPDATE user SET brojSobe= '" + brojSobe +"' WHERE userName = "+ gost.getUserName();
		
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}