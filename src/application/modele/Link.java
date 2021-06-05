package application.modele;

//PAS REFACTORISE

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Link extends Personnage{

	private ObservableList <Equipement> inventaire ;
	private ObservableList<Heart> hearts;
	private Equipement equipementEnMain;
	private boolean grab;

	
	public Link(double x, double y, String id, Environnement world){
		super(x, y, id, world,100);

		this.inventaire = FXCollections.observableArrayList();
		this.equipementEnMain = null;
		
		/*TEST AVANT SPAWN*/
		Epee epee = new Epee();
		Arc arc = new Arc();
		this.inventaire.add(epee);
		this.inventaire.add(arc);
		this.grab = false;
	}
	
	public Deplacables changeCaisse() {
		for (Deplacables caisse: this.world.getListeDeco()) {
			if(	(getY()-48<= caisse.getY() && caisse.getY()<=getY()+48) 
					&& (getX()-48<= caisse.getX() && caisse.getX()<=getX()+48) ){
				return caisse;
			}
		}
		return null;
	}
	
	public void grabObjet() {
		this.grab = true;
	}
	
	public void lacher() {
		this.grab = false;
	}
	
	public boolean getGrab() {
		return this.grab;
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
				System.out.println("A vos garde chevalier ! Epee en main");
			}
			else if (this.equipementEnMain instanceof Arc){
				System.out.println("A distance chevalier ! Arc en main");
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
