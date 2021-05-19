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
		if(direction.equalsIgnoreCase("Top"))
			this.setY(getY()-32);
		else if(direction.equalsIgnoreCase("Down"))
			this.setY(getY()+32);
		else if(direction.equalsIgnoreCase("Right"))
			this.setX(getX()+32);
		else 
			this.setX(getX()-32);
	}


}
