package hotel_management;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {
	
	public void updateUser() throws SQLException;
	public void dodajUser() throws SQLException;
	public void odjaviUser() throws SQLException;
	public void pretraziUsers() throws SQLException;
	public ArrayList<User> getUsers() throws SQLException;	
	
}
