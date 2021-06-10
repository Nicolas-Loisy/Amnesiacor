package application.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;

public class Fleche {
	
	private static int nombreFleche = 1;

	public String id;
	public int degat;
	public String direction;
	private int portee;
	public boolean flecheCasse;
	
	private DoubleProperty x,y;
	
	public Fleche (double x, double y, String direction) {
		this.id = "fleche"+nombreFleche;
		this.degat = 25;
		this.direction = direction;
		this.portee = 16;
		this.x = new SimpleDoubleProperty(x+16);
		this.y = new SimpleDoubleProperty(y);
		
		nombreFleche++;
		System.out.println("new fleche : "+ this.id);
		
		this.flecheCasse = false;
	}
	

	public void moveFleche(Environnement world) {

		if(this.direction == "Up" ){
			if(world.marcheSurCase(calculCASEx(), calculCASEy()-1)){
				this.setY(getY()-32);
			}
			else {
				this.flecheCasse=true;
			}
		}	
		else if (this.direction == "Down"){
			if(world.marcheSurCase(calculCASEx(), calculCASEy()+1)){
				this.setY(getY()+32);
			}
			else {
				this.flecheCasse=true;
			}
		}
		else if (this.direction == "Right"){
			if(world.marcheSurCase(calculCASEx()+1, calculCASEy())){
				this.setX(getX()+32);
			}
			else {
				this.flecheCasse=true;
			}
		}
		else if (this.direction == "Left"){
			if(world.marcheSurCase(calculCASEx()-1, calculCASEy())){
				this.setX(getX()-32);
			}
			else {
				this.flecheCasse=true;
			}
		}
	}
	public String getDirection() {
		return this.direction;
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
	
	
	public int calculCASEx(){
		return (int)Math.floor((this.getX()/32));
	}
	public int calculCASEy(){
		return (int)Math.ceil((this.getY()/32));
	}
	
	
	public boolean attaque (Environnement world) {
		Goblins gob = world.ennemiClose(getX(), getY(), portee);
		
		if(gob != null) {
			gob.perteDeVie(this.getPointDegat());
			this.flecheCasse=true;
			return true;
		}
		else{
			System.out.println("Pas d'ennemis fleche!!");
			return false;
		}
	}
	
	
}
