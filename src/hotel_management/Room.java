package hotel_management;

/**
 * Klasa Room
 */

public class Room {
	
	//Osobine
	private int number;
	private String type;
	private int dayPrice;
	private boolean occupied;
	
	//Konstruktor bez argumenata
	public Room(){
	}
	
	//Konstruktor sa odredjenim vrijednostima
	public Room(int number, String type, int dayPrice, boolean occupied){
		this.number = number;
		this.type = type;
		this.dayPrice = dayPrice;
		this.occupied = occupied;
	}

	//Geteri i seteri
	public boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public int getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}

	public int getDayPrice() {
		return dayPrice;
	}
	
	//Ispisuje objekat
	@Override
	public String toString(){
		return "(Room)Number: " + this.getNumber() + ",  Type: " + this.getType() 
				+ ",  Day price: " + this.getDayPrice() + ",  Occupied: " + this.getOccupied()  ;
		
	}
}
