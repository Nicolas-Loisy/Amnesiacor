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

		Goblins gob = getEnnemiDirection(world, x, y, direction);
		
		if(gob != null) {
			gob.perteDeVie(this.getPointDegat());
		}
		else{
			System.out.println("Pas d'ennemis!!");
		}
	}
	

	/*public Goblins ennemiClose(Environnement world, double x, double y) {
		for(Goblins gob : world.getListeGoblins()){
				if(	(y-48<= gob.getY() && gob.getY()<=y+48) 
						&& (x-48<= gob.getX() && gob.getX()<=x+48) ){
					return gob;
				}				
		}
		return null;
	}*/
	
	public Goblins getEnnemiDirection(Environnement world, double x, double y, String direction) {
		for(Goblins gob : world.getListeGoblins()){
			if(direction == "Up") {
				if((y-48<= gob.getY() && gob.getY()<=y) 
						&& (x == gob.getX()) ){
					return gob;
				}				
			}
			else if(direction == "Down") {
				if((y<= gob.getY() && gob.getY()<=y+48) 
						&& (x == gob.getX()) ){
					return gob;
				}				
			}
			else if(direction == "Left") {
				if((x-48<= gob.getX() && gob.getX()<=x) 
						&& (y == gob.getY()) ){
					return gob;
				}				
			}
			else if(direction == "Right") {
				if((x<= gob.getX() && gob.getX()<=x+48) 
						&& (y == gob.getY()) ){
					return gob;
				}				
			}		
		}
		return null;
	}
	

}