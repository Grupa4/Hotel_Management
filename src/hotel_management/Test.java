package hotel_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) throws SQLException {
		
		
	System.out.println(roomsExist());	
		
		
		
	}
		
	public static boolean roomsExist(){
		String query = "SELECT * FROM rooms";
		boolean roomsAlreadyThere=false;

		// Provjera username-a
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
		
		