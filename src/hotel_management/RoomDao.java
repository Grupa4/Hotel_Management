package hotel_management;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomDao {
	
	public void updateRoom() throws SQLException;
	public void occupyRoom() throws SQLException;
	public void vacatyRoom() throws SQLException;
	public ArrayList<Room> getRooms() throws SQLException;	

}
