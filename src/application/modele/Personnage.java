package application.modele;
//PAS REFACTORISÉ
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public abstract class Personnage {
	private String id;
	private DoubleProperty x,y;//pixels
	private IntegerProperty CASE_X,CASE_Y;//"CARREAUX"
	private int pv;
	protected Environnement world;

	public Personnage(double x, double y, String id, Environnement world, int ptsVie){
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		this.CASE_X = new SimpleIntegerProperty((int)Math.floor((this.getX()/32)));// refaire apres same w/bind
		this.CASE_Y = new SimpleIntegerProperty((int) Math.ceil((this.getY()/32)));
		this.id = id;
		this.world = world;
		this.pv = ptsVie;
	}
	public Personnage( String id, Environnement world, int ptsVie){
		do {
			this.x = new SimpleDoubleProperty( 32* (int)(Math.random()*11) );
			this.y = new SimpleDoubleProperty(-16 +(32 * (int)(Math.random()*11)));
			
		} while ( !(world.availablePosition(x.getValue(), y.getValue())) || !(world.marcheSurCase((int)Math.floor(x.getValue()/32), (int)Math.floor(y.getValue()/32))));
		
		this.CASE_X = new SimpleIntegerProperty((int)Math.floor((this.getX()/32)));// refaire apres same w/bind
		this.CASE_Y = new SimpleIntegerProperty((int) Math.ceil((this.getY()/32)));
		this.id = id;
		this.world = world;
		this.pv = ptsVie;
	}
	
	
	
	public String getId() {
		return this.id;
	}
	
	public int getPv() {
		return this.pv;
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
	
	public int calculCASEx(){
		return (int)Math.floor((this.getX()/32));
	}
	public int calculCASEy(){
		return (int)Math.ceil((this.getY()/32));
	}
	public final void setPersoTab() {//permet d'avoir la position par rapport au tille
		this.setPersoCASE_X(calculCASEx());
		this.setPersoCASE_Y(calculCASEy());
		//gère le horsMap
		if(this.getPersoCASE_X() < 0) setPersoCASE_X(0);
		if(this.getPersoCASE_Y() < 0) setPersoCASE_Y(0);
		//System.out.println("Link: X["+CASE_X+"] ; Y["+CASE_Y+"]"+"& ["+this.getX()+"] ; ["+this.getY()+"]");
	}
	
	public void perteDeVie(int degat) {//faire un exception 
		if (this.pv==0){
			
		}
		else {
			this.pv = this.pv-degat;
		}
	}
	public boolean stillAlive() {
		return this.pv > 0;
	}
	
	public void move(String direction) {
		if(direction.equalsIgnoreCase("Up")&& world.availablePosition(getX(),getY()-32))
			this.setY(getY()-32);
		else if(direction.equalsIgnoreCase("Down") && world.availablePosition(getX(),getY()+32))
			this.setY(getY()+32);
		else if(direction.equalsIgnoreCase("Right") && world.availablePosition(getX()+32,getY()))
			this.setX(getX()+32);
		else if(direction.equalsIgnoreCase("Left") && world.availablePosition(getX()-32,getY()))
			this.setX(getX()-32);
	}
	
	

}
