package hotel_management;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {

	// ispravila sam ovdje u zagradama samo dodala podatak po kojem ce metoda da
	// radi. znaci update po objektu a odjavit mozemo i na osnovu imena kao i
	// pretrazit
	public void updateUser(User gost) throws SQLException;

	public void dodajUser(User gost) throws SQLException;

	public void odjaviUser(String name) throws SQLException;

	public void pretraziUsers(String name) throws SQLException;

	public ArrayList<User> getUsers() throws SQLException;

}
