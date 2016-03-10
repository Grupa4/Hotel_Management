package hotel_management;

public class Usluge {

	// data filds za klasu usluge
	private int idUsluge;
	private String nazivUsluge;
	private double cijenaUsluge;

	// no arg konstruktor
	public Usluge() {

	}

	// konstruktor sa data fildsima
	public Usluge(int idUsluge, String nazivUsluge, double cijenaUsluge) {
		this.idUsluge = idUsluge;
		this.nazivUsluge = nazivUsluge;
		this.cijenaUsluge = cijenaUsluge;
	}
	// geteri i seteri

	public int getIdUsluge() {
		return idUsluge;
	}

	public void setIdUsluge(int idUsluge) {
		this.idUsluge = idUsluge;
	}

	public String getNazivUsluge() {
		return nazivUsluge;
	}

	public void setNazivUsluge(String nazivUsluge) {
		this.nazivUsluge = nazivUsluge;
	}

	public double getCijenaUsluge() {
		return cijenaUsluge;
	}

	public void setCijenaUsluge(double cijenaUsluge) {
		this.cijenaUsluge = cijenaUsluge;
	}

	// metoda koja ispisuje
	public String toString() {
		return "idUsluge " + idUsluge + " nazivUsluge " + nazivUsluge + " cijenaUsluge " + cijenaUsluge;
	}

}
