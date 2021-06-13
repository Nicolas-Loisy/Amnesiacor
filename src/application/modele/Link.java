package application.modele;

import application.exceptions.PersonnageExceptions;

//PAS REFACTORISE

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;


public class Link extends Personnage{

	private ObservableList <Equipement> inventaire ;
	private Equipement equipementEnMain;
	private boolean grab;
	private Deplacables objet;

	
	public Link(double x, double y, String id, Environnement world){

		super(x, y, id, world,15);
		this.inventaire = FXCollections.observableArrayList();
		this.equipementEnMain = null;
		Epee epee = new Epee(world);
		Arc arc = new Arc(world);
		this.inventaire.add(epee);
		this.inventaire.add(arc);
		this.grab = false;
		this.objet = null;
	}
	
	public Deplacables detecteDeplacables() {
		for (Objets obj: this.world.getListeObject()) {
			if (obj instanceof Deplacables) {
				if(	(getY()-48<= obj.getYobj() && obj.getYobj()<=getY()+48) 
						&& (getX()-48<= obj.getXobj() && obj.getXobj()<=getX()+48) ){
					return (Deplacables) obj;
				}
				
			}
			
		}
		return null;
	}
	
	public void grabObjet(Deplacables objet) {
		this.grab = true;
		this.objet = objet;
	}
	
	public Deplacables getObjet() {
		return this.objet;
	}
	
	public void lacher() {
		this.grab = false;
		this.objet = null;
	}
	
	public void deplaceObjet(String deplacement) {
		if (getGrab()) {
			getObjet().seDeplace(deplacement);
		}
	}	
	
	public boolean getGrab() {
		return this.grab;
	}

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
	
	/*GESTION VIE*/
	public void RecupHearts(){
			int i = 0;
			//toRemove
			int toRem = -1;//pour pas modifier une liste que tu traite
			for (Objets h : world.getListeObject()) {
				if (h instanceof Heart) {
					Heart hTemp = (Heart) h;
					if ( (Math.round(h.getXobj())==Math.round(this.getX())) && (Math.round(h.getYobj())==Math.round(this.getY())) ){
						try {
							this.addLife(hTemp.getValue());
							toRem = i;
						} catch (PersonnageExceptions e) {
							if (!this.stillAlive())
								System.out.println("You're dead");
							else 
								System.out.println("full life");
						}
					}
				}
				i++;
			}
			if (toRem>=0) {
				world.getListeObject().remove(toRem);		
			}
		}
	
	
	public void setWorld(Environnement world) {
		this.world = world;
	}
}
