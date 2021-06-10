package application.modele;

public abstract class Armes extends Equipement{
	
	private int degat;
	protected Environnement world;
	
	public Armes (String id, int degat, Environnement world) {
		super(id);
		this.degat = degat;
		this.world = world;
	}
	
	public int getPointDegat() {
		return this.degat;
	}
	
	public abstract void attaque (double x, double y, String direction, Environnement world);

}