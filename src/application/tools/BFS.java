package application.tools;

import java.util.ArrayList;
import java.util.HashMap;

import application.modele.Environnement;
import application.modele.Goblins;
import application.modele.Link;
import javafx.scene.control.Tab;

public class BFS {
	private Environnement word;
	private Link link;
	private ArrayList<Integer>ListeWorkable;
	HashMap<Integer, Integer> sizeWays;
	
	public BFS(Environnement e, Link link) {
		word = e;
		ListeWorkable = word.getListeMarchable();
		sizeWays = new HashMap<Integer, Integer>();
		this.link = link;
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean validCase(int x, int y ){//sert a rien
		if(word.marcheSurCase(x, y)) {
			return true;
		}
		return false;
	}
	
	public int calculCase(int x, int y){//return le numero de la case
		return (y*20+x);
	}
	public int backToX(int numCase){
		return (numCase%20);
	}
	public int backToY(int numCase) {
		System.out.println(numCase);
		return ((numCase-(backToX(numCase)))/20);
	}
	
	
	public void findAWay(){//BFS s'active uniquement quand le link sera a un certain emplacement
		ArrayList<Integer>Queue = new ArrayList<>();
		ArrayList<Integer>Past = new ArrayList<>();//déja passé
		
		int size = 1;//map's value
		int i=0;

		Queue.add(calculCase(link.getPersoCASE_X(), link.getPersoCASE_Y()));
		Past.add(calculCase(link.getPersoCASE_X(), link.getPersoCASE_Y()));
		
		int[][]currentCase ={{link.getPersoCASE_X()},{link.getPersoCASE_Y()}};
		
		while(Queue != null){// TW && (currentCase[0][0] != link.getPersoCASE_X() && currentCase[1][0] != link.getPersoCASE_Y())
			
			if(word.marcheSurCase(currentCase[0][0], currentCase[1][0]+1) && Past.contains(calculCase(currentCase[0][0], currentCase[1][0]+1))==false ){//UP
				Queue.add(calculCase(currentCase[0][0], currentCase[1][0]+1));
				Past.add(calculCase(currentCase[0][0], currentCase[1][0]+1));
				sizeWays.put(calculCase(currentCase[0][0], currentCase[1][0]+1), size);
				
			}
			if(word.marcheSurCase(currentCase[0][0], currentCase[1][0]-1) && Past.contains(calculCase(currentCase[0][0], currentCase[1][0]-1))==false ){//DOWN
				Queue.add(calculCase(currentCase[0][0], currentCase[1][0]-1));
				Past.add(calculCase(currentCase[0][0], currentCase[1][0]-1));
				sizeWays.put(calculCase(currentCase[0][0], currentCase[1][0]-1), size);
			}
			if(word.marcheSurCase(currentCase[0][0]+1, currentCase[1][0]) && Past.contains(calculCase(currentCase[0][0]+1, currentCase[1][0]))==false ){//LEFT
				Queue.add(calculCase(currentCase[0][0]+1, currentCase[1][0]));
				Past.add(calculCase(currentCase[0][0]+1, currentCase[1][0]));
				sizeWays.put(calculCase(currentCase[0][0]+1, currentCase[1][0]), size);
			}
			if(word.marcheSurCase(currentCase[0][0]-1, currentCase[1][0]) && Past.contains(calculCase(currentCase[0][0]-1, currentCase[1][0]))==false ){//LEFT
				Queue.add(calculCase(currentCase[0][0]-1, currentCase[1][0]));
				Past.add(calculCase(currentCase[0][0]-1, currentCase[1][0]));
				sizeWays.put(calculCase(currentCase[0][0]-1, currentCase[1][0]), size);
			}
			
			size++;
			//enlève la case en tete de file
			Queue.remove(0);
			//change la case case observée
			currentCase[0][0] = backToX(Queue.get(0));
			currentCase[0][1] =	backToY(Queue.get(0));
		}
	}
	
	public void displaySizeWay() {
		System.out.println(sizeWays);
	}

}
