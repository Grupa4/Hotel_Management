package hotel_management;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {

	public void updateUser(User gost) throws SQLException;

	public void dodajUser(User gost) throws SQLException;
	
	public void obrisiUser(String idCard) throws SQLException;

	public void odjaviUser(String idCard) throws SQLException;

	public ArrayList<User> pretraziUsersName(String name) throws SQLException;
	
	public ArrayList<User> pretraziUsersIdCard(String idCard) throws SQLException;
	
	public String provjeriKorisnika(String username, String password) throws SQLException;

	public ArrayList<User> getUsers() throws SQLException;

	public void checkInUser(String idKorisnika) throws SQLException;
	
	public void checkOutUser(String idKorisnika) throws SQLException;

	public void promjenaSobe(User gost, int brojSobe) throws SQLException;

}
