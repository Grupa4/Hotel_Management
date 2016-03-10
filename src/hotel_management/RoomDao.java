package hotel_management;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interfejs sadrzi neophodne metode 
 * za rad sa sobama
 */

public interface RoomDao {
	
	public boolean isFreeRoom(int number) throws SQLException;//Provjerava da li je soba prazna
	public void occupyRoom(int number) throws SQLException;//Popunjava zadatu sobu
	public void vacateRoom(int number) throws SQLException;//Prazni zadatu sobu
	public ArrayList<Room> getRooms() throws SQLException;//Vraca listu svih soba	
	public ArrayList<Room> getFreeRooms() throws SQLException;//Vraca listu slobodnih soba	

}
