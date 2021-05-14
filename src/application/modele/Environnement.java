package application.modele;

import java.util.ArrayList;

public class Environnement {
	private int widthTab,heightTab;
	private int[][]land; //FileReader
	
	public Environnement(int w, int h,int l, int c ){
		widthTab= w;
		heightTab = h;
		land = new int [l][c];	
	}
	
	
	
	public int[][] fillTheLand(){
		return this.land;
	}
	
	

}
