package hotel_management;

public class OdnosUserUsluge {

	// data filds
	private int userID;
	private int idUsluge;
	private int userUslugeID;

	// no arg konstruktor
	public OdnosUserUsluge() {

	}

	// konstrukotr sa data fildsima
	public OdnosUserUsluge(int userID, int idUsluge, int userUslugeID) {
		this.userID = userID;
		this.idUsluge = idUsluge;
		this.userUslugeID = userUslugeID;
	}

	// geteri i seteri
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getIdUsluge() {
		return idUsluge;
	}

	public void setIdUsluge(int idUsluge) {
		this.idUsluge = idUsluge;
	}

	public int getUserUslugeID() {
		return userUslugeID;
	}

	public void setUserUslugeID(int userUslugeID) {
		this.userUslugeID = userUslugeID;
	}

	// metoda za ispis
	public String toString() {
		return "userID " + userID + " idUsluge " + idUsluge + " userUslugeID " + userUslugeID;
	}

}
