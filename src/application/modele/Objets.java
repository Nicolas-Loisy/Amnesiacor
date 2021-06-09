package application.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Objets {
	
	private DoubleProperty x, y;
	private IntegerProperty X, Y;
	private String id;	
	private Environnement world;
	
	public Objets( Environnement world,String id) {
		this.id = id;
		do {
			this.x = new SimpleDoubleProperty(32* (int)(Math.random()*11) );
			this.y = new SimpleDoubleProperty(-16 +(32 *(int)(Math.random()*11)));
			
		} while ( !(world.availablePosition(this.x.getValue(), this.y.getValue())) || !(world.marcheSurCase((int)Math.floor(this.x.getValue()/32), (int)Math.floor(this.y.getValue()/32))));
		this.X = new SimpleIntegerProperty((int)Math.floor((this.getXobj()/32)));
		this.Y = new SimpleIntegerProperty((int)Math.floor((this.getYobj()/32)));
}
	
	public Double getXobj() {
		return this.x.getValue();
	}
	public Double getYobj() {
		return this.y.getValue();
	}
	
	public DoubleProperty getXobjProperty() {
		return this.x;
	}
	public DoubleProperty getYobjProperty() {
		return this.y;
	}
	public final void setXobj(double d){
		x.setValue(d);
	}
	
	public final void setYobj(double d){
		y.setValue(d);
	}
	
	public final int getCASE_X(){
		return X.getValue();
	}
	public final int getCASE_Y() {
		return Y.getValue();
	}
	
	
	public String getId() {
		return this.id;
	}

}