package hotel_management;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {

	// ispravila sam ovdje u zagradama samo dodala podatak po kojem ce metoda da
	// radi. znaci update po objektu a odjavit mozemo i na osnovu imena kao i
	// pretrazit
	public void updateUser() throws SQLException;

	public void dodajUser() throws SQLException;

	public void odjaviUser() throws SQLException;

	public void pretraziUsers() throws SQLException;

	public ArrayList<User> getUsers() throws SQLException;

	// Evo i ja sam dodao jos jednu metodu. Kada je pokrenemo sistem od tog
	// trenutnka primi gosta u hotel. Uveo sam ovo zbog metode dodajUser, u
	// njoj bismo samo trebali dodati korisnika kojeg nema u bazi, a ukoliko bi
	// neki stari korisnik dosao onda ga samo nadjemo u bazi i checkiramo u
	// hotel
	public void checkInUser() throws SQLException;

	// evo ja sam dodala metodu ako je zanovjetalo pa hoce da mijenja sobu
	public void promjenaSobe(User gost, int brojSobe) throws SQLException;

}
