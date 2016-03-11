package hotel_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UslugeDaoConcrete implements UslugeDao {

	// mijenja postojecu uslugu
	@Override
	public void updateUsluge() throws SQLException {
		
		java.util.Scanner unos = new java.util.Scanner(System.in);

		System.out.println("Unesite ID usluge: ");
		int idUsluge = unos.nextInt();
		unos.nextLine();
		System.out.println("Unesite naziv usluge");
		String nazivUsluge = unos.nextLine();
		System.out.println("Unesite cijenu usluge");
		double cijenaUsluge = unos.nextDouble();

		Usluge usluga = new Usluge(idUsluge, nazivUsluge, cijenaUsluge);

		ConnectDB db = new ConnectDB();
		db.updateUsluge(usluga);
		
	}

	// Dodaje uslugu u tabelu
	@Override
	public void dodajUsluge() throws SQLException {
		java.util.Scanner unos = new java.util.Scanner(System.in);

		System.out.println("Unesite ID usluge: ");
		int idUsluge = unos.nextInt();
		unos.nextLine();
		System.out.println("Unesite naziv usluge");
		String nazivUsluge = unos.nextLine();
		System.out.println("Unesite cijenu usluge");
		double cijenaUsluge = unos.nextDouble();

		Usluge usluga = new Usluge(idUsluge, nazivUsluge, cijenaUsluge);

		ConnectDB db = new ConnectDB();
		db.insertUsluge(usluga);
	}

	// Brise uslugu iz tabele
	@Override
	public void obrisiUsluge() throws SQLException {
		java.util.Scanner unos = new java.util.Scanner(System.in);

		System.out.println("Unesite ID usluge: ");
		int idUsluge = unos.nextInt();
		unos.nextLine();

		ConnectDB db = new ConnectDB();
		db.obrisiUsluge(idUsluge);

	}
	
	//Spisak usluga
	@Override
	public ArrayList<Usluge> getUsluge() throws SQLException {
		
		String query = "SELECT * FROM services";
		ArrayList<Usluge> list = new ArrayList<>();
		
		try(Connection connection = ConnectDB.getConnected();
			Statement statement = connection.createStatement();) {
			
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				Usluge usluga = new Usluge(resultSet.getInt(1), resultSet.getString(2),resultSet.getDouble(3));
				list.add(usluga);
			}
			resultSet.close();
		}catch (SQLException e){
			System.out.println(e.toString());
		}
		
		return list;
		
	}

}
