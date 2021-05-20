package application.modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Environnement {
	private int widthTab,heightTab;
	private int[][]land; //FileReader
	private ObservableList<Goblins>Liste_Goblins;
	
	public Environnement(int w, int h,int l, int c ){
		widthTab= w;
		heightTab = h;
		land = new int [l][c];
		this.Liste_Goblins = FXCollections.observableArrayList();
	}
	
	public ObservableList<Goblins> getListeGoblins(){
		return Liste_Goblins;
	}
	
	public void addGoblins(Goblins g) {
		Liste_Goblins.add(g);
	}
	
	
	
	public int[][] fillTheLand(){
		return this.land;
	}
	
	

}
