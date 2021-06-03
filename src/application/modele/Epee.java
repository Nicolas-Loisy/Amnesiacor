package application.modele;

public class Epee extends Armes{
	public static int numEpee = 1;
	public Epee () {
		super("Epee"+numEpee, 25);
		numEpee++;
	}
	
	public void attaque (Environnement world) {
		
	}

}