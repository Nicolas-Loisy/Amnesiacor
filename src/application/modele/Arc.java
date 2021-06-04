package application.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Arc extends Armes{

	public static int numArc = 1;
	public Arc () {
		super("Arc"+numArc, 10);
		numArc++;
	}
	
	
	
	public void attaque(double x, double y, String direction, Environnement world) {
		System.out.println("ARC");
		Fleche fleche = new Fleche(x, y, direction);
		world.addFleches(fleche);
	}
	//methode add fleches list Env
	
	
	public Goblins ennemiClose(Environnement world, double x, double y) {
		for(Goblins gob : world.getListeGoblins()){
				if(	(y-48<= gob.getY() && gob.getY()<=y+48) 
						&& (x-48<= gob.getX() && gob.getX()<=x+48) ){
					return gob;
				}				
		}
		return null;
	}	
	
	
	
	
}