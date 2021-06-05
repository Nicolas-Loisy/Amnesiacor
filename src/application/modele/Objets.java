package application.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Objets {
	
	private DoubleProperty x, y;
	private IntegerProperty X, Y;
	public static int numObj = 1;
	private String id;
	
	public Objets (double x, double y) {
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		this.X = new SimpleIntegerProperty((int)Math.floor((this.getX()/32)));
		this.Y = new SimpleIntegerProperty((int)Math.floor((this.getY()/32)));
		this.id = "caisse" + numObj;
		numObj++;
	}
	
	public Objets( Environnement world) {
		
		do {
			this.x = new SimpleDoubleProperty( 32* (int)(Math.random()*11) );
			this.y = new SimpleDoubleProperty(-16 +(32 * (int)(Math.random()*11)));
			
		} while ( !(world.availablePosition(this.x.getValue(), this.y.getValue())) || !(world.marcheSurCase((int)Math.floor(this.x.getValue()/32), (int)Math.floor(this.y.getValue()/32))));
	}
	
	public final DoubleProperty getPropertyX() {
		return this.x;
	}
	
	public final DoubleProperty getPropertyY() {
		return this.y;
	}
	
	public String getId() {
		return this.id;
	}
	public final double getX() {
		return x.getValue();
	}
	public final double getY() {
		return y.getValue();
	}
	
	public final int getPersoCASE_X(){
		return X.getValue();
	}
	public final int getPersoCASE_Y() {
		return Y.getValue();
	}
	
	public final void setY(double d){
		y.setValue(d);
	}
	
	public final void setX(double d){
		x.setValue(d);
	}

}