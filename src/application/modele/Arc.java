package application.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Arc extends Armes{
	
	private ObservableList <Fleche> fleches ;
	
	public Arc () {
		super("Arc", 1);
		this.fleches = FXCollections.observableArrayList();
	}
	
	public ObservableList<Fleche> getListFleche(){
		return this.fleches;
	}
	
	
	
	public void attaque (Environnement world) {
		
	}

	
	public void tirer(Environnement world) {
		
	}
	
	@Override
	public int getPointDegat() {
		return 0;
	}

}