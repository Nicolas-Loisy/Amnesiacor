package application.modele;

public class Epee extends Armes{
	public static int numEpee = 1;
	public Epee () {
		super("Epee"+numEpee, 25);
		numEpee++;
	}
	
	public void attaque (Environnement world) {
		
		/*System.out.println("attaqueEpee");
		//Goblins gob = super.world.ennemiClose();
		Goblins gob = ennemiClose2();
		if(gob != null) {
			gob.perteDeVie(((Armes) this.equipementEnMain).getPointDegat());
		}
		else{
			System.out.println("Pas d'ennemis!!");
		}*/
	}

}