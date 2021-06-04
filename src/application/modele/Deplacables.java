package application.modele;

import javafx.beans.property.IntegerProperty;

public class Cassables {
	
	private int x, y;
	private IntegerProperty X, Y;
	public static int num = 0;
	private String id;
	
	public Cassables (int x, int y) {
		this.x = x;
		this.y = y;
		this.X = SimpleIntegerProperty(x);
		this.Y = SimpleIntegerProperty(y);
		this.id = "caisse" + num;
		num++;
	}
	
	private IntegerProperty SimpleIntegerProperty(int x2) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getDecoX() {
		return this.x;
	}
	
	public IntegerProperty getPropertyX() {
		return this.X;
	}
	
	public IntegerProperty getPropertyY() {
		return this.Y;
	}
	
	public String getId() {
		return this.id;
	}
	
	public int getDecoY() {
		return this.y;
	}
	
	public void seDeplace(String direction) {
		if(direction.equalsIgnoreCase("Up"))
			this.y = this.y - 32;

		else if(direction.equalsIgnoreCase("Down"))
			this.y = this.y + 32;
		
		else if(direction.equalsIgnoreCase("Right"))
			this.x = this.x + 32;
		
		else 
			this.x = this.x - 32;
	}

}
