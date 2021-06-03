package application.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.KeyCode;

public class Fleche {
	
	
	private static int nombreFleche = 1;
	public String id;
	public int degat;
	public String direction;
	
	private DoubleProperty x,y;
	private int CASE_X,CASE_Y;
	
	public Fleche (double x, double y, String direction) {
		this.id = "fleche"+nombreFleche;
		this.degat = 25;
		this.direction = direction;
		
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		
		this.CASE_X = (int) Math.floor((this.getX()/32));// refaire apres same w/bind
		this.CASE_Y = (int) Math.ceil((this.getY()/32));
		
		nombreFleche++;
	}
	
	
	public void moveFleche(Environnement world) {
		if(this.direction == "Up" ){
			if(world.marcheSurCase(this.CASE_X, this.CASE_Y-1)){
				
			}	
			
		}	
		else if (this.direction == "Down"){
			if(world.marcheSurCase(this.CASE_X, this.CASE_Y+1)){
				
			}
			
		}
		else if (this.direction == "Right"){
			if(world.marcheSurCase(this.CASE_X+1, this.CASE_Y)){
				
			}
			
		}
		else if (this.direction == "Left"){
			if(world.marcheSurCase(this.CASE_X-1, this.CASE_Y)){
				
			}
			
		}
	}
	
	public int getPointDegat() {
		return this.degat;
	}
	
	public String getId() {
		return this.id;
	}

	public final Double getX() {
		return this.x.getValue();
	}
	public final void setX(double d){
		this.x.setValue(d);
	}
	public final DoubleProperty getxProporty() {
		return this.x;	
	}

	public final double getY() {
		return this.y.getValue();
	}
	public final void setY(double d){
		this.y.setValue(d);
	}
	public final DoubleProperty  getyProporty() {
		return this.y;
	}
	
	public final int getCaseX() {
		return this.CASE_X;
	}
	public final int getCaseY() {
		return this.CASE_Y;
	}
	
	public void attaque (Environnement world) {
		
	}
	
	
	
}
