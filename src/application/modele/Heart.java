package application.modele;

public class Heart extends Objets{
	private int value;
	private String id;
	private static int cmpt = 0;
	
	public Heart(Environnement world){
		super(world);
		this.value = 25;
		this.id = "Coeur"+cmpt;
		cmpt++;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getId() {
		return this.id;
	}

}
