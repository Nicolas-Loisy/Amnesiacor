package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Link extends Personnage{
	private IntegerProperty x,y;
	private String id; 
	
	public Link(int x, int y, String id){
		super(x, y, id);
	}

	@Override
	public void move(String direction) {
		if(direction.equalsIgnoreCase("Top")) {
			this.setY(getY()-32);
			printCoo();
		}	
		else if(direction.equalsIgnoreCase("Down"))
			this.setY(getY()+32);
		else if(direction.equalsIgnoreCase("Right"))
			this.setX(getX()+32);
		else 
			this.setX(getX()-32);
	}
	
	
	
	
	public void printCoo() {
		System.out.println("x=" + this.getX()/32 + " y=" + (this.getY()+16)/32);
	}
	
	public int getXcase() {
		return this.getX()/32;
	}
	
	public int getYcase() {
		return (this.getY()+16)/32;
	}
	
	
	
}
