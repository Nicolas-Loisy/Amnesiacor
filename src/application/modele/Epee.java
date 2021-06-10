package application.modele;

public class Epee extends Armes{
	public static int numEpee = 1;
	private int portee;
	
	public Epee (Environnement world) {
		super("Epee"+numEpee, 25,world);
		numEpee++;
		this.portee = 48; //porte enemie close
	}
	
	public void attaque(double x, double y, String direction, Environnement world) {
		System.out.println("attaqueEpee");
		Goblins gob = this.world.ennemiClose(x, y, portee);		
		if(gob != null) {
			gob.perteDeVie(this.getPointDegat());
		}
		else{
			System.out.println("Pas d'ennemis!!");
		}
	}
	
}