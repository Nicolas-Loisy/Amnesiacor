package application.modele;

import java.util.ArrayList;
import java.util.Arrays;

import application.tools.JsonReader;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Environnement {
	private int widthTab,heightTab;

	private Link link;	
	private int[][]land; //FileReader
	private ArrayList<Integer> caseMarchable = new ArrayList<>();
	private ObservableList<Goblins>Liste_Goblins;
	private ObservableList<Cassables>listeDeco;
	
		
	public Environnement(int w, int h,int l, int c,Link link ){
		widthTab= w;
		heightTab = h;
		land = new int [l][c];
		this.link = link;
		
		this.Liste_Goblins = FXCollections.observableArrayList();
		this.listeDeco = FXCollections.observableArrayList();
		
		caseMarchable = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 14, 15, 18, 21, 22, 36, 37, 38, 41, 42, 43, 47, 48, 54, 84, 85, 89, 90, 96, 97, 98, 99, 119, 161, 162, 171, 172, 192, 205, 210, 215, 220, 225, 230, 233, 235));   
		
		try {
			land = JsonReader.chargerTableau("img/minishMAP.json").clone();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ObservableList<Goblins> getListeGoblins(){
		return Liste_Goblins;
	}
	
	public ObservableList<Cassables> getListeDeco() {
		return listeDeco;
	}
	
	public Cassables changeDeco1() {
		for (Cassables obj: this.getListeDeco()) {
			if(	(this.link.getY()-48<= obj.getDecoY() && obj.getDecoY()<=this.link.getY()+48) 
					&& (this.link.getX()-48<= obj.getDecoX() && obj.getDecoX()<=this.link.getX()+48) ){
				return obj;
			}
		}
		return null;
	}

	public Goblins ennemiClose() {
		for(Goblins gob : this.getListeGoblins()){//bof    verification autour    48= 32+16 16 because link est middle case donc 16pxl
				if(	(this.link.getY()-48<= gob.getY() && gob.getY()<=this.link.getY()+48) 
						&& (this.link.getX()-48<= gob.getX() && gob.getX()<=this.link.getX()+48) ){
					return gob;
				}			
		}
		return null;
	}
	
	public void addDecorations(Cassables c) {
		listeDeco.add(c);
	}
	
	public void addGoblins(Goblins g) {
		Liste_Goblins.add(g);
	}
	
	
	public int[][] fillTheLand(){
		return this.land;
	}
	
	
	public int[][] getLand(){
		return this.land;
	}
	
	public void afficheLand() {
		for (int i = 0; i < this.land.length; i++) {
			for (int j = 0; j < this.land[i].length; j++) {
				System.out.println(this.land[i][j]);
			}
		}
	}

	public void afficheListe() {
		System.out.println(this.caseMarchable.toString());
	}
	public boolean marcheSurCase(int x, int y) {
		//System.out.println(this.land[x][y]);
		if(x < 0 || x > 19){
			return false;
		}
		if(y < 0 || y > 19){
			return false;
		}
		return this.caseMarchable.contains(this.land[y][x]); //inversion x et y car tab java
	}
	public ArrayList<Integer> getListeMarchable(){
		return caseMarchable;
	}



}
