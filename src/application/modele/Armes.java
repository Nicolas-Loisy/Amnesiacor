package application.modele;

public abstract class Armes extends Equipement{
	
	private String id;
	private int degat;
	
	public Armes (String id, int degat) {
		super(id);
		this.degat = degat;
	}
	
	public abstract int getPointDegat();
	
	public abstract void attaque (Environnement world);

}