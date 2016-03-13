package hotel_management;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {

	public void updateUser(User gost) throws SQLException;

	public void dodajUser(User gost) throws SQLException;

	public void odjaviUser(String idCard) throws SQLException;

	public void pretraziUsersName(String name) throws SQLException;
	
	public void pretraziUsersIdCard(String idCard) throws SQLException;

	public ArrayList<User> getUsers() throws SQLException;

	public void checkInUser(User user) throws SQLException;
	
	public void checkOutUser(User user) throws SQLException;

	public void promjenaSobe(User gost, int brojSobe) throws SQLException;

}
