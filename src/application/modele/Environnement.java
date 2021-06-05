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

	private int[][]land; //FileReader
	private ArrayList<Integer> caseMarchable = new ArrayList<>();

	private ObservableList<Goblins>Liste_Goblins;	
	private ObservableList<Fleche>listeFleches;
	
	private ObservableList<Deplacables>listeCaisse;
	private ObservableList<Object>liste_Objets;
	
	public Environnement(){
		this.Liste_Goblins = FXCollections.observableArrayList();
		this.listeFleches = FXCollections.observableArrayList();
		this.listeCaisse = FXCollections.observableArrayList();

		
		caseMarchable = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 14, 15, 18, 21, 22, 36, 37, 38, 41, 42, 43, 47, 48, 54, 84, 85, 89, 90, 96, 97, 98, 99, 119, 161, 162, 171, 172, 192, 205, 210, 215, 220, 225, 230, 233, 235));
		
		try {
			land = JsonReader.chargerTableau("img/minishMAP.json").clone();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.widthTabTiles = land.length;
		this.heightTabTiles= land[0].length;	
		
		this.widthTabPix= widthTabTiles*32;
		this.heightTabPix= heightTabTiles*32;
	}
	
	public void addDecorations(Deplacables c) {
		listeCaisse.add(c);
	}
	
	public void addGoblins(Goblins g) {
		Liste_Goblins.add(g);
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
	
	
	public ObservableList<Deplacables> getListeDeco() {
		return listeCaisse;
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
	public boolean availablePosition(double x, double y){
		for (Goblins g : getListeGoblins()){
			if(g.getX()==x && g.getY() == y) 
				return false;
		}
		return true;
	}
	
	public void pickUpTheDead() {
		for (int i = 0; i < Liste_Goblins.size(); i++) {
			if (!Liste_Goblins.get(i).stillAlive()) {
				Liste_Goblins.remove(i);
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
	public ObservableList<Goblins> getListeGoblins(){
		return Liste_Goblins;
	}
	
	public int[][] getLand(){
		return this.land;
	}

}
