package application.tools;

import java.util.ArrayList;

import application.modele.Environnement;
import application.modele.Goblins;
import application.modele.Link;

public class BFS {
	Environnement word;
	ArrayList<Integer>ListeWorkable;
	
	public BFS(Environnement e) {
		word = e;
		//ListeWorkable =
		// TODO Auto-generated constructor stub
	}
	
	
	public void validCase(int X, int Y ){
		
	}
	
	public void findAWay(Link link){//BFS s'active uniquement quand le link sera a un certain emplacement
		ArrayList<Integer>Queue = new ArrayList<>();
		Goblins g = word.getListeGoblins().get(0);
	}

}
