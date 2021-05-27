package application.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Link extends Personnage{

	private String id; 
	
	public Link(double x, double y, String id){

		super(x, y, id);
	}

	@Override
	public void move(String direction) {
		if(direction.equalsIgnoreCase("Up"))
			this.setY(getY()-32);

		else if(direction.equalsIgnoreCase("Down"))
			this.setY(getY()+32);
		
		else if(direction.equalsIgnoreCase("Right"))
			this.setX(getX()+32);
		
		else 
			this.setX(getX()-32);
	}
	
}
