package application.modele;

public class Recuperables extends Objets{
	private static int numObj=1;
	
	public Recuperables (Environnement world) {
		super(world, "objet"+numObj);
		numObj++;
	}

}
