package hotel_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	public void odjaviUser(String name) throws SQLException {
		// sa ovom metodom odjavljujemo gosta iz nase baze ali nam njegovi
		// podaci ostaju

		// query da izaberemo gosta na osnovu imena tj koga zelimo odjavimo
		String query = "SELECT FROM user WHERE name = " + name;
		// skener za citanje sa konzole
		Scanner input = new Scanner(System.in);
		System.out.println("Unesite ime gosta kojeg zelite odjaviti:");
		// smjetamo unos sa konzole tj koga zelimo da odjavimo
		String nameZaOdjavu = input.next();
		// zatvoren skener
		input.close();
		// drugi query koji ce da nam promijeni u tabeli stanje gosta tj da ga
		// odjavi
		String query1 = "UPDATE users set userLogged WHERE name = " + nameZaOdjavu;
		ResultSet rs = null;
		// konekcija sa bazom
		try (Connection connection = ConnectDB.getConnected(); Statement statement = connection.createStatement();) {
			// updajte query1
			statement.executeUpdate(query1);
			rs = statement.executeQuery(query);
			rs.next();
			// ispis
			System.out.println("Vas gost " + rs.getString("name") + " uspjesno je odjavljen iz hotela");
		}
		rs.close();
	}

	@Override
	public void pretraziUsers(String name) throws SQLException {
		// trazimo goste u bazi na osnovu imena
		Scanner input = new Scanner(System.in);
		//ispis
		System.out.println("Unesite ime kojeg gosta trazite");
		//smjestamo koje ime trazimo
		String gostIme = input.next();
		//zatvoren skener
		input.close();
		//query
		String query = "SELECT from users WHERE name = " + gostIme;
		ResultSet rs = null;
		//konekcija i izvrsavanje querija
		try (Connection connection = ConnectDB.getConnected(); Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(query);
			//ispis
			System.out.println("Podaci o vasem trazenom gostu su :" + rs.getString("name") + rs.getString("surname")
					+ rs.getInt("age") + rs.getString("idCard") + rs.getInt("roomNumber") + rs.getInt("roomType")
					+ rs.getDate("chekIn") + rs.getBoolean("userAtivan") + rs.getDate("checkOut")
					+ rs.getArray("services"));
		}
	}

	@Override
	public ArrayList<User> getUsers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkInUser() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void promjenaSobe(String name, int brojSobe) throws SQLException {
		// TODO Auto-generated method stub

	}

}