package application.modele;

import java.util.ArrayList;

public class Environnement {
	private int widthTab,heightTab;
	private int[][]terrain; //FileReader
	
	public Environnement(int w, int h,int l, int c ){
		widthTab= w;
		heightTab = h;
		terrain = new int [l][c];	
	}
	
	

}
