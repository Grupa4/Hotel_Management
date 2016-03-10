package hotel_management;

public class Room {
	
	private int number;
	private String type;
	private int dayPrice;
	private boolean occupied;
	
	public Room(){
	}
	
	public Room(int number, String type, int dayPrice, boolean occupied){
		this.number = number;
		this.type = type;
		this.dayPrice = dayPrice;
		this.occupied = occupied;
	}

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
	
	@Override
	public String toString(){
		return "Number: " + this.getNumber() + " Type: " + this.getType() 
				+ " Day price: " + this.getDayPrice() + " Occupied: " + this.getOccupied()  ;
		
	}
}
