package application.modele;
//PAS REFACTORISÉ
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
				System.out.println("à vos garde chevalier ! Epée en main");
			}
			else if (this.equipementEnMain instanceof Arc){
				System.out.println("à distance chevalier ! Arc en main");
			}
		}
	}

	public void attaque() {
		if(this.equipementEnMain instanceof Epee) {
			//System.out.println("Epee");
			attaqueEpee();
		}
		else if(equipementEnMain instanceof Arc) {// idée attaque arc si toucher bouge pas !
			System.out.println("ARC");
			
		}
	}
	
	
	public void attaqueEpee() {
		System.out.println("attaqueEpee");
		//Goblins gob = super.world.ennemiClose();
		Goblins gob = ennemiClose2();
		if(gob != null) {
			gob.perteDeVie(((Armes) this.equipementEnMain).getPointDegat());
		}
		else{
			System.out.println("Pas d'ennemis!!");
		}
	}
	
	
	
	//provisoire TEST
	public Goblins ennemiClose2() {
		for(Goblins gob : super.world.getListeGoblins()){//bof    verification autour    48= 32+16 16 because link est middle case donc 16pxl
				if(	(getY()-48<= gob.getY() && gob.getY()<=getY()+48) 
						&& (getX()-48<= gob.getX() && gob.getX()<=getX()+48) ){
					return gob;
				}				
		}
		return null;
	}
	/*FIN PAR A REFAIRE*//////////////////////////////////////////////////////////////////
	
	
}
