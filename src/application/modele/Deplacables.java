package application.modele;



public class Deplacables extends Objets{
	
	
	public Deplacables (double x, double y) {
		super(x, y);
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
