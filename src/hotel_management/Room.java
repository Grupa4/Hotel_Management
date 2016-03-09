package hotel_management;

public class Room {
	
	private int number;
	private String type;
	private int dayPrice;
	private char ocupied;
	
	public Room(){
	}
	
	public Room(int number, String type, int dayPrice, char ocupied){
		this.number = number;
		this.type = type;
		this.dayPrice = dayPrice;
		this.ocupied = ocupied;
	}

	public char getOcupied() {
		return ocupied;
	}

	public void setOcupied(char ocupied) {
		this.ocupied = ocupied;
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
}
