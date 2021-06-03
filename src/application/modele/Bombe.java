package application.modele;

public class Bombe extends Equipement{
	public static int numBombe = 1;
	public Bombe () {
		super("Bombe"+numBombe);
		numBombe++;
	}

}