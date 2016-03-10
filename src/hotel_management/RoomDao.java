package hotel_management;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomDao {
	
	public void updateRoom(int number) throws SQLException;
	public void occupyRoom(int number) throws SQLException;
	public void vacatyRoom(int number) throws SQLException;
	public ArrayList<Room> getRooms() throws SQLException;	

}
