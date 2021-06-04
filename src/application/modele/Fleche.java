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
	
	private DoubleProperty x,y;
	//private IntegerProperty caseX,caseY;
	
	public Fleche (double x, double y, String direction) {
		this.id = "fleche"+nombreFleche;
		this.degat = 25;
		this.direction = direction;
		
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		
		//this.caseX = new SimpleIntegerProperty((int)Math.floor((this.getX()/32)));
		//this.caseY = new SimpleIntegerProperty((int) Math.ceil((this.getY()/32)));
		
		nombreFleche++;
		System.out.println("new fleche : "+ this.id);
	}
	
	
	public boolean moveFleche(Environnement world) {
		if(this.direction == "Up" ){
			if(world.marcheSurCase(calculCASEx(), calculCASEy()-1)){
				this.setY(getY()-32);
				return true;
			}
			else {
				return false;
			}
		}	
		else if (this.direction == "Down"){
			if(world.marcheSurCase(calculCASEx(), calculCASEy()+1)){
				this.setY(getY()+32);
				return true;
			}
			else {
				return false;
			}
		}
		else if (this.direction == "Right"){
			if(world.marcheSurCase(calculCASEx()+1, calculCASEy())){
				this.setX(getX()+32);
				return true;
			}
			else {
				return false;
			}
		}
		else if (this.direction == "Left"){
			if(world.marcheSurCase(calculCASEx()-1, calculCASEy())){
				this.setX(getX()-32);
				return true;
			}
			else {
				return false;
			}
		}
		return false;
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
	
	/*
	public final int getCaseX() {
		return this.caseX;
	}
	public final int getCaseY() {
		return this.caseY;
	}*/
	
	
	public int calculCASEx(){
		return (int)Math.floor((this.getX()/32));
	}
	public int calculCASEy(){
		return (int)Math.ceil((this.getY()/32));
	}
	
	
	public boolean attaque (Environnement world) {
		Goblins gob = ennemiClose(world, this.getX(), this.getY());
		
		if(gob != null) {
			gob.perteDeVie(this.getPointDegat());
			return true;
		}
		else{
			System.out.println("Pas d'ennemis fleche!!");
			return false;
		}
	}
	
	public Goblins ennemiClose(Environnement world, double x, double y) {
		for(Goblins gob : world.getListeGoblins()){
				if(	(y-16<= gob.getY() && gob.getY()<=y+16) 
						&& (x-16<= gob.getX() && gob.getX()<=x+16) ){
					return gob;
				}				
		}
		return null;
	}	
	
}
