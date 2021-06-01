package application.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Link extends Personnage{

	private String id;
	private boolean arme;
	private ObservableList <Equipement> inventaire ;
	//private Environnement world;    WARNING deja un world dans le super classe au dessus
	
	private Equipement equipementEnMain;
	
	public Link(double x, double y, String id, Environnement world){
		super(x, y, id, world);
		this.arme = false;
		this.inventaire = FXCollections.observableArrayList();
		this.equipementEnMain = null;
		
		Epee epee = new Epee();
		Arc arc = new Arc();
		this.inventaire.add(epee);
		this.inventaire.add(arc);
	}

	@Override
	public void move(String direction) {
		if(direction.equalsIgnoreCase("Up"))
			this.setY(getY()-32);

		else if(direction.equalsIgnoreCase("Down"))
			this.setY(getY()+32);
		
		else if(direction.equalsIgnoreCase("Right"))
			this.setX(getX()+32);
		
		else 
			this.setX(getX()-32);
	}
	
	public void equipe() {
		this.arme = true;
	}
	public void desequipe() {
		this.arme = false;
	}
	
	public Equipement armeDeCombat() {
		for(Equipement a : this.inventaire){
			if(a instanceof Armes) {
				return a;
			}
				
		}
		return null;
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
	
	
	public void gestionEquipement(int numEquipement) {
		if(this.equipementEnMain == this.inventaire.get(numEquipement)) {
			System.out.println("se desequipe");
			this.equipementEnMain = null;
		}
		else {
			System.out.println("s'equipe");
			this.equipementEnMain = this.inventaire.get(numEquipement);
		}
	}
	
	public void attaque2() {
		if(this.equipementEnMain instanceof Epee) {
			//System.out.println("Epee");
			attaqueEpee();
		}
		else if(equipementEnMain instanceof Arc) {
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
	
	
	
}
