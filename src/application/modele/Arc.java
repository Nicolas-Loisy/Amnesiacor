package application.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Arc extends Armes{

	public static int numArc = 1;
	public Arc () {
		super("Arc"+numArc, 10);
		numArc++;
	}
	
	
	
	public void attaque (Environnement world) {
		
	}
	//methode add fleches list Env

}