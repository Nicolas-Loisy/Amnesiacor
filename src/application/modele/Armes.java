package application.modele;

public abstract class Armes extends Equipement{
	
	public int degat;

	
	public Armes (String id, int degat) {
		super(id);
		this.degat = degat;
	}
	
	public int getPointDegat() {
		return this.degat;
	}
	
	public abstract void attaque (double x, double y, String direction, Environnement world);

}