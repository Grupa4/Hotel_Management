package hotel_management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomDaoConcrete implements RoomDao {

	@Override
	public void updateRoom(int number) throws SQLException {
		
	}

	@Override
	public void occupyRoom(int number) throws SQLException {
		String query = "UPDATE room SET occupied = 'true' WHERE number = '" + number + "'";
		
		try(Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			
			statement.executeUpdate(query);
			System.out.println("Successfully updated the table.");
		} catch (SQLException e) {
			System.err.println(e.toString());
		}		
	}

	@Override
	public void vacatyRoom(int number) throws SQLException {
		String query = "UPDATE room SET occupied = 'false' WHERE number = '" + number + "'";
		
		try(Connection connection = ConnectDB.getConnected();
				Statement statement = connection.createStatement();) {
			
			statement.executeUpdate(query);
			System.out.println("Successfully updated the table.");
		} catch (SQLException e) {
			System.err.println(e.toString());
		}
		
	}

	@Override
	public ArrayList<Room> getRooms() throws SQLException {
		String query = "SELECT * FROM room";
		ArrayList<Room> list = new ArrayList<>();
		
		try(Connection connection = ConnectDB.getConnected();
			Statement statement = connection.createStatement();) {
			
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				Room room = new Room();
				list.add(room);
			}
			resultSet.close();
		}catch (SQLException e){
			System.out.println(e.toString());
		}
		return list;
	}
}
