package application.modele;

public class Deplacables extends Objets{
	private static int numObjdep = 1;
	
	public Deplacables (Environnement world) {
		super(world, "ObjetDep"+numObjdep);
		numObjdep++;
	}
	
	
	public void seDeplace(String direction) {
		if(direction.equalsIgnoreCase("Up"))
			this.setYobj(getYobj()-32);
		else if(direction.equalsIgnoreCase("Down"))
			this.setYobj(getYobj()+32);
		else if(direction.equalsIgnoreCase("Right"))
			this.setXobj(getXobj()+32);
		else 
			this.setXobj(getXobj()-32);
	}

}