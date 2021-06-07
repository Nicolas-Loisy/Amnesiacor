package application.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Objets {
	private DoubleProperty x,y;
	private Environnement world;
	private String id;
	
	public Objets( Environnement world,String id) {
		this.id = id;
		do {
			this.x = new SimpleDoubleProperty(32* (int)(Math.random()*11) );
			this.y = new SimpleDoubleProperty(-16 +(32 *(int)(Math.random()*11)));
			
		} while ( !(world.availablePosition(this.x.getValue(), this.y.getValue())) || !(world.marcheSurCase((int)Math.floor(this.x.getValue()/32), (int)Math.floor(this.y.getValue()/32))));
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
	
	
	
	
	public String getId() {
		return this.id;
	}

}
