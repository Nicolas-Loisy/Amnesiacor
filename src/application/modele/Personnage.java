package application.modele;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public abstract class Personnage {
	private DoubleProperty x,y;//pixels
	private int CASE_X,CASE_Y;//"CARREAUX"
	private String id;
	private int pv;
	public Environnement world;


	public Personnage(double x, double y, String id, Environnement world){
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		this.CASE_X = (int) Math.floor((this.getX()/32));// refaire apres same w/bind
		this.CASE_Y = (int) Math.ceil((this.getY()/32));
		this.id = id;
		this.pv = 5;
		this.world = world;
	}
	
	public String getId() {
		return this.id;
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
	
	public int getPersoCASE_X(){
		return CASE_X;
	}
	public int getPersoCASE_Y() {
		return CASE_Y;
	}
	
	public void getPersoTab() {//permet d'avoir la position par rapport au tille
		CASE_X = (int) Math.floor((this.getX()/32));
		CASE_Y = (int) Math.ceil((this.getY()/32));
		if(CASE_X < 0) CASE_X = 0;
		if(CASE_Y < 0) CASE_Y = 0;
		//System.out.println("Link: X["+CASE_X+"] ; Y["+CASE_Y+"]"+"& ["+this.getX()+"] ; ["+this.getY()+"]");
	}
	
	public void perteDeVie(int degat) {
		this.pv = this.pv-degat;
		
	}
	
	public abstract void attaque();
	
	public abstract void move(String direction);

}
