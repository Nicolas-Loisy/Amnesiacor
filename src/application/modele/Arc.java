package application.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Arc extends Armes{

	public static int numArc = 1;
	public Arc (Environnement world) {
		super("Arc"+numArc, 0,world);
		numArc++;
	}
	
	public void attaque(double x, double y, String direction, Environnement world) {
		System.out.println("ARC");
		Fleche fleche = new Fleche(x, y, direction);
		world.addFleches(fleche);
	}
}