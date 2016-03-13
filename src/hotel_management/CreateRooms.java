package hotel_management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateRooms extends ConnectDB {
	
	Room jednokrevetna1=new Room(10, "jednokrevetna", 20, false);
	Room jednokrevetna2=new Room(11, "jednokrevetna", 20, false);
	Room jednokrevetna3=new Room(12, "jednokrevetna", 20, false);
	Room dvokrevetna1=new Room(20, "dvokrevetna", 40, false);
	Room dvokrevetna2=new Room(21, "dvokrevetna", 40, false);
	
	public CreateRooms() throws SQLException {
		//Ukoliko nema soba u bazi kreiramo ih
		if (!roomsExist()) {
		updateRoom(jednokrevetna1);
		updateRoom(jednokrevetna2);
		updateRoom(jednokrevetna3);
		updateRoom(dvokrevetna1);
		updateRoom(dvokrevetna2);
		}
	}
	
	
	//Metoda za provjeru je li sobe vec postoje
	public boolean roomsExist(){
		String query = "SELECT * FROM rooms";
		boolean roomsAlreadyThere=false;

		// Provjera ima li ista u bazi podataka rooms
		try (Statement statement = ConnectDB.getConnected().createStatement();) {
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				roomsAlreadyThere=true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return roomsAlreadyThere;
	}
	
	
}
