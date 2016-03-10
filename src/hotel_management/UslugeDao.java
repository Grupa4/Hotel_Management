package hotel_management;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UslugeDao {
	
	public void updateUsluge() throws SQLException;
	public void dodajUsluge() throws SQLException;
	public void obrisiUsluge() throws SQLException;
	public ArrayList<Usluge> getUsluge() throws SQLException;	
	
	
}
