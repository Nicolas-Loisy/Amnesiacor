package application.modele;
//PAS REFACTORISE

import java.util.ArrayList;
import java.util.Arrays;
import application.tools.JsonReader;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Environnement {
	private int widthTabPix,heightTabPix;
	private int widthTabTiles,heightTabTiles;
	private Link link;
	private int[][]land; //FileReader
	private ArrayList<Integer> caseMarchable = new ArrayList<>();

private ObservableList<Goblins>liste_Goblins;	
	private ObservableList<Fleche>listeFleches;
	private ObservableList<Objets>liste_Objets;
	
	public Environnement(Link link){
		this.link = link;
		this.liste_Goblins = FXCollections.observableArrayList();
		this.listeFleches = FXCollections.observableArrayList();
		//UNE DES DEUX DOIT SAUTER
		this.liste_Objets = FXCollections.observableArrayList();

		
		caseMarchable = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 14, 15, 18, 21, 22, 36, 37, 38, 41, 42, 43, 47, 48, 54, 84, 85, 89, 90, 96, 97, 98, 99, 119, 161, 162, 171, 172, 192, 205, 210, 215, 220, 225, 230, 233, 235));
		
		try {
			land = JsonReader.chargerTableau("img/newMinish.json").clone();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.widthTabTiles = land.length;
		this.heightTabTiles= land[0].length;	
		
		this.widthTabPix= widthTabTiles*32;
		this.heightTabPix= heightTabTiles*32;
		
	}
	
	
	public void addGoblins(Goblins g) {
		liste_Goblins.add(g);
	}
	public void addObjets(Objets obj) {
		liste_Objets.add(obj);
	}
	

	/* FONCTION LISTE FLECHES */
	public ObservableList<Fleche> getListeFleches(){
		return listeFleches;
	}
	public void addFleches(Fleche fleche) {
		listeFleches.add(fleche);
	}	
	public void removeFleches(Fleche fleche) {
		listeFleches.remove(listeFleches.indexOf(fleche));		
	}
	
	
	public boolean inMap(int x, int y){
		if(x < 0 || x > widthTabTiles-1){
			return false;
		}
		if(y < 0 || y > heightTabTiles-1){
			return false;
		}
		return true;
	}
	
	public boolean marcheSurCase(int x, int y) {
		if (!inMap(x, y))
			return false;
		return this.caseMarchable.contains(this.land[y][x]); //inversion x et y car tab java
	}
	
	public boolean availablePositionSpawn(double x, double y){
		for (Goblins g : getListeGoblins()){
			if(g.getX()==x && g.getY() == y || g.getMonEnnemi().getX() == x && g.getMonEnnemi().getY() == y ) 
				return false;
		}
		for (Objets g : getListeObject()){
			if (g instanceof Deplacables) {
				if(g.getXobj()==x && g.getYobj() == y) 
					return false;
			}
		}
		if (link.getX() == x && link.getY()== y)
			return false;
		
		//trouver moyen recup link
		
		if(!inMap((int)Math.floor((x/32)),(int)Math.ceil((y/32))))
			return false;
		return true;
	}
	
	public boolean availablePositionWalk(double x, double y) {
		for (Goblins g : getListeGoblins()){
			if(g.getX()==x && g.getY() == y ) 
				return false;
		}
		for (Objets o : getListeObject()){
			if (o instanceof Deplacables) {
				if(o.getXobj()==x && o.getYobj() == y) 
					return false;
			}
		}
		if(!inMap((int)Math.floor((x/32)),(int)Math.ceil((y/32))))
			return false;
		return true;
	}
	
	public Goblins ennemiClose( double x, double y,int portee) {
		for(Goblins gob : getListeGoblins()){
				if(	(y-portee<= gob.getY() && gob.getY()<=y+portee) 
						&& (x-portee<= gob.getX() && gob.getX()<=x+portee) ){
					return gob;
				}				
		}
		return null;
	}
	
	public void pickUpTheDead() {
		for (int i = 0; i < liste_Goblins.size(); i++) {
			if (!liste_Goblins.get(i).stillAlive()) {
				liste_Goblins.remove(i);
			}
		}
	}
	
	public ArrayList<Integer> getListeMarchable(){
		return caseMarchable;
	}
	public int GetWidthTabTiles(){
		return this.widthTabTiles;	
	}
	public int GetHeightTabTiles(){
		return this.heightTabTiles;
	}
	public int getWidthTabPix() {
		return widthTabPix;
	}
	public int getHeightTabPix() {
		return heightTabPix;
	}
	
	
	
	public ObservableList<Goblins> getListeGoblins(){
		return liste_Goblins;
	}
	public ObservableList<Objets> getListeObject(){
		return liste_Objets;
	}
	
	public int[][] getLand(){
		return this.land;
	}

}
