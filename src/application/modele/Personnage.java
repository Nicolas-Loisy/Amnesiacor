package application.modele;
//PAS REFACTORISE

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public abstract class Personnage {
	private String id;
	protected DoubleProperty x,y;//pixels
	protected IntegerProperty CASE_X,CASE_Y;//"CARREAUX"
	private IntegerProperty pv;
	protected Environnement world;
	
	private String viewDirection;


	public Personnage(double x, double y, String id, Environnement world, int ptsVie){
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		this.CASE_X = new SimpleIntegerProperty((int)Math.floor((this.getX()/32)));// refaire apres same w/bind
		this.CASE_Y = new SimpleIntegerProperty((int) Math.ceil((this.getY()/32)));
		this.id = id;
		this.world = world;
		this.viewDirection = "Down";
		this.pv = new SimpleIntegerProperty(ptsVie);
	}
	
	public Personnage( String id, Environnement world, int ptsVie){
		do {
			this.x = new SimpleDoubleProperty( 32* (int)(Math.random()*11) );
			this.y = new SimpleDoubleProperty(-16 +(32 * (int)(Math.random()*11)));
			
		} while ( !(world.availablePositionSpawn(x.getValue(), y.getValue())) || !(world.marcheSurCase((int)Math.floor(x.getValue()/32), (int)Math.floor(y.getValue()/32))));
		
		this.CASE_X = new SimpleIntegerProperty((int)Math.floor((this.getX()/32)));// refaire apres same w/bind
		this.CASE_Y = new SimpleIntegerProperty((int) Math.ceil((this.getY()/32)));
		this.id = id;
		this.world = world;
		this.pv = new SimpleIntegerProperty(ptsVie);
	}
	
	public Personnage(Environnement world, String id,int ptsvie){
		do{
			this.x = new SimpleDoubleProperty( 32* (int)(Math.random()*11) );
			this.y = new SimpleDoubleProperty(-16 +(32 * (int)(Math.random()*11)));
		}while ( !(world.availablePositionSpawn(x.getValue(), y.getValue())) );
		this.CASE_X = new SimpleIntegerProperty((int)Math.floor((this.getX()/32)));// refaire apres same w/bind
		this.CASE_Y = new SimpleIntegerProperty((int) Math.ceil((this.getY()/32)));
		this.world = world;
		this.id = id;
		this.pv = new SimpleIntegerProperty(ptsvie);
		
		
	}
	
	
	
	public String getId() {
		return this.id;
	}
	
	public int getPv() {
		return this.pv.getValue();
	}
	public void setPv(int value) {
		this.pv.setValue(value);
	}
	public IntegerProperty getPvProperty() {
		return this.pv;
	}

	public String getViewDirection() {
		return this.viewDirection;
	}

	
	// PIXEL POSITION ///////////////////////////////////////////////
	public final Double getX() { 		 						   //
		return x.getValue();									   //
	}														       //
																   //
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
		return y;										           //
	}													           //
	/////////////////////////////////////////////////////////////////
	
	
	// CASE POSITION ///////////////////////////////////////////////
	public final int getPersoCASE_X(){							  //
		return CASE_X.getValue();								  //
	}															  //		
	public final int getPersoCASE_Y() {
		return CASE_Y.getValue();
	}
	
	public final void setPersoCASE_X(int e) {
		CASE_X.setValue(e);
	}
	
	public final void setPersoCASE_Y(int e) {
		CASE_Y.setValue(e);
	}
	
	public final IntegerProperty getCASE_XProporty() {
		return CASE_X;
	}
	
	public final IntegerProperty getCASE_YProporty() {		
		return CASE_Y;											   //		
	}															   //
	/////////////////////////////////////////////////////////////////
	
	public final void persoTabListener() {//permet d'avoir la position par rapport au tille
		getxProporty().addListener((obs,old,nouv)->{
			setPersoCASE_X((int)Math.floor(((double)nouv/32) ));
			if(this.getPersoCASE_X() < 0) setPersoCASE_X(0);
		});
		getyProporty().addListener((obs,old,nouv)->{
			setPersoCASE_Y((int)Math.ceil(((double)nouv/32) ));
			if(this.getPersoCASE_Y() < 0) setPersoCASE_Y(0);
		});
		//gere le horsMap		
		//System.out.println("Link: X["+CASE_X+"] ; Y["+CASE_Y+"]"+"& ["+this.getX()+"] ; ["+this.getY()+"]");
	}
	
	public void perteDeVie(int degat) {//faire un exception 
		if (getPv()-degat < 0){
			setPv(0);
		}
		else {
			setPv(getPv()-degat);
		}
	}
	
	public boolean stillAlive() {
		if (getPv()==0)
			System.out.println(this.id + " est mort");
		return getPv() > 0;
	}
	
	public void move(String direction) {
		this.viewDirection = direction;
		if(direction.equalsIgnoreCase("Up")&& world.availablePositionWalk(getX(),getY()-32))
			this.setY(getY()-32);
		else if(direction.equalsIgnoreCase("Down") && world.availablePositionWalk(getX(),getY()+32))
			this.setY(getY()+32);
		else if(direction.equalsIgnoreCase("Right") && world.availablePositionWalk(getX()+32,getY()))
			this.setX(getX()+32);
		else if(direction.equalsIgnoreCase("Left") && world.availablePositionWalk(getX()-32,getY()))
			this.setX(getX()-32);
	}
	
	

}
