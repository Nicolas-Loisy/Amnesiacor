package application.modele;



public class Deplacables extends Objets{
	
	
	public Deplacables (double x, double y, Environnement world) {
		super(x, y, world);
	}
	
	
	public void seDeplace(String direction) {
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
