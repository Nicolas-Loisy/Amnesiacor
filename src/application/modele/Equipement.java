package application.modele;

public abstract class Equipement {
	
	private String id;
	public static int num=0;
	
	public Equipement (String id) {
		
		this.id = id + num;
		num++;
		
	}

}