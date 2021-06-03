package application.modele;
//PAS REFACTORISE
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Link extends Personnage{

	private ObservableList <Equipement> inventaire ;
	private Equipement equipementEnMain;
	
	
	
	public Link(double x, double y, String id, Environnement world){
		super(x, y, id, world,100);

		this.inventaire = FXCollections.observableArrayList();
		this.equipementEnMain = null;
		
		/*TEST AVANT SPAWN*/
		Epee epee = new Epee();
		Arc arc = new Arc();
		this.inventaire.add(epee);
		this.inventaire.add(arc);
	}


	/*A REFAIRE!!*/////////////////////////////////////////////////////////////////////////
	public void gestionEquipement(int numEquipement) {
		if(this.equipementEnMain == this.inventaire.get(numEquipement)) {
			System.out.println("se desequipe");
			this.equipementEnMain = null;
		}
		else {
			this.equipementEnMain = this.inventaire.get(numEquipement);	
			if (this.equipementEnMain instanceof Epee){
				System.out.println("Ã vos garde chevalier ! Epee en main");
			}
			else if (this.equipementEnMain instanceof Arc){
				System.out.println("Ã distance chevalier ! Arc en main");
			}
		}
	}

	public void attaque() {
		if(this.equipementEnMain instanceof Armes) {
			((Armes)this.equipementEnMain).attaque(super.getX(), super.getY(), super.getViewDirection(), super.world);
		}
	}
	
	/*FIN PAR A REFAIRE*//////////////////////////////////////////////////////////////////

	
	
}
