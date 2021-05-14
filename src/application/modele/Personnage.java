package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {
	private IntegerProperty x,y;
	private String id;
	
	public Personnage(int x, int y, String id){
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.id = id;
	}
	public String getId() {
		return this.id;
	}

	public final int getX() {
		return x.getValue();
	}

	public final void setX(int n){
		x.setValue(n);
	}
	public final IntegerProperty getxProporty() {
		return x;
		
	}

	public final int getY() {
		return y.getValue();
	}
	public final void setY(int n){
		y.setValue(n);
	}
	public final IntegerProperty getyProporty() {
		return y;
	}
	
	public abstract void move(String direction);

}
