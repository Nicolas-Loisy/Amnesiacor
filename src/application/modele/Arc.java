package application.modele;

public class Arc extends Armes{
	public static int numArc = 1;
	public Arc () {
		super("Arc"+numArc, 10);
		numArc++;
	}
	
	public void attaque (Environnement world) {
		
	}
}