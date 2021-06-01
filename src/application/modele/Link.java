package application.modele;
//PAS REFACTORISÉ
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Link extends Personnage{

	private String id;
	private boolean arme;
	private ObservableList <Equipement> inventaire ;

	
	public Link(double x, double y, String id, Environnement world){
		super(x, y, id, world);
		this.arme = false;
		this.inventaire = FXCollections.observableArrayList();
	}

	public void equipe() {
		this.arme = true;
	}
	
	public Equipement armeDeCombat() {
		for(Equipement a : this.inventaire){
			if(a instanceof Armes) {
				return a;
			}
				
		}
		return null;
	}
	
	public void desequipe() {
		this.arme = false;
	}
	
	/*public int damageGive() {
		
		Armes arme = ((Armes) armeDeCombat());
		return arme.getPointDegat();
	}*/
	
	public void attaque() {
		Armes arme = ((Armes) armeDeCombat());
		
		if (arme instanceof Epee) {
			arme.attaque(world);
		}
		
		/*Goblins gob = this.world.ennemiClose();
		gob.perteDeVie(damageGive());*/
	}
	
	public boolean enMain() {
		return this.arme;
	}
	
}
