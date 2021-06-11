package application.modele;

public class Heart extends Objets {
	private int regenValue;

	private static int cmpt = 1;
		public Heart(Environnement world) {
		super(world,"Coeur"+cmpt);
		this.regenValue = 25;
		cmpt++;
		}	
		
		public int getValue(){
			return this.regenValue;
		}

}
