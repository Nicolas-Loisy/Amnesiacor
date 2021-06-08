package application.modele;

public class Hearts extends Objets {
	private int regenValue;

	private static int cmpt = 1;
		public Hearts(Environnement world) {
		super(world,"Coeur"+cmpt);
		this.regenValue = 25;
		cmpt++;
		}	
		
		public int getValue(){
			return this.regenValue;
		}

}
