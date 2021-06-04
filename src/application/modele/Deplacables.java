package application.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Deplacables {
	
	private DoubleProperty x, y;
	//private IntegerProperty X, Y;
	public static int num = 0;
	private String id;
	
	public Deplacables (double x, double y) {
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		//this.X = SimpleIntegerProperty(x);
		//this.Y = SimpleIntegerProperty(y);
		this.id = "caisse" + num;
		num++;
	}
	
	public final Double getX() { 		 						   
		return x.getValue();									   
	}														       
																   
	public final void setX(double d){
		x.setValue(d);
	}
	public final DoubleProperty getxProporty() {
		return x;	
	}
	public final double getY() {
		return y.getValue();
	}
	public final void setY(double d){
		y.setValue(d);
	}
	public final DoubleProperty  getyProporty() {
		return y;										           
	}
	
	/*public IntegerProperty getPropertyX() {
		return this.X;
	}
	
	public IntegerProperty getPropertyY() {
		return this.Y;
	}*/
	
	public String getId() {
		return this.id;
	}
	
	
	
	public void seDeplace(String direction) {
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
