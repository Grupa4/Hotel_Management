package hotel_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Klasa sadrzi metode za manipulaciju podacima
 * vezanim za sobe
 */

public class RoomDaoConcrete implements RoomDao {

	/**Provjerava da li je soba prazna*/
	@Override
	public boolean isFreeRoom(int number) throws SQLException {
		String query = "SELECT occupied FROM rooms WHERE number = " + number;
	
		try(Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();
			if(resultSet.getString(1).equals("0"))
				return true;
		} catch (SQLException e) {
			System.err.println(e.toString());
		}			
		return false;
	}

	/**Popunjava sobu*/
	@Override
	public void occupyRoom(int number) throws SQLException {
		String query = "UPDATE rooms SET occupied = true WHERE number = " + number ;
		
		try(Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			if(isFreeRoom(number)){
				statement.executeUpdate(query);
				System.out.println("Successfully updated the table.");
			}else {
				System.out.println("Room already occupied!");
			}
		} catch (SQLException e) {
			System.err.println(e.toString());
		}		
	}

	/**Prazni sobu*/
	@Override
	public void vacateRoom(int number) throws SQLException {
		String query = "UPDATE rooms SET occupied = false WHERE number = " + number + "";
		
		try(Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			if(isFreeRoom(number)){
				System.out.println("Room already free!");
			}else{
				statement.executeUpdate(query);
				System.out.println("Successfully updated the table.");
			}
		} catch (SQLException e) {
			System.err.println(e.toString());
		}	
	}

	/**Vraca sve sobe*/
	@Override
	public ArrayList<Room> getRooms() throws SQLException {
		String query = "SELECT * FROM rooms";
		ArrayList<Room> list = new ArrayList<>();
		
		try(Connection connection = ConnectDB.getConnected();
			Statement statement = connection.createStatement();) {
			
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				Room room = new Room(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getBoolean(4));
				list.add(room);
			}
			resultSet.close();
		}catch (SQLException e){
			System.out.println(e.toString());
		}
		return list;
	}
	
	/**Vraca prazne sobe*/
	@Override
	public ArrayList<Room> getFreeRooms() throws SQLException {
		String query = "SELECT * FROM rooms WHERE occupied = false";
		ArrayList<Room> list = new ArrayList<>();
		
		try(Connection connection = ConnectDB.getConnected();
			Statement statement = connection.createStatement();) {
			
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				Room room = new Room(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getBoolean(4));
				list.add(room);
			}
			resultSet.close();
		}catch (SQLException e){
			System.out.println(e.toString());
		}
		return list;
	}
}