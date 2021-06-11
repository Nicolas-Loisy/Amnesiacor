package application.tools;
//PAS REFACTORISE

import java.util.ArrayList;
import java.util.HashMap;

import application.modele.Environnement;
import application.modele.Goblins;
import application.modele.Link;
import javafx.scene.control.Tab;
import sun.net.www.content.text.plain;

public class BFS {
	private Environnement world;
	private Link link;
	private ArrayList<Integer>ListeWorkable;
	HashMap<Integer, Integer> sizeWaysGobT;
	HashMap<Integer, Integer> sizeWaysGobV;
	
	public BFS(Environnement e, Link link) {
		world = e;
		this.link = link;
		ListeWorkable = world.getListeMarchable();
		sizeWaysGobT = new HashMap<Integer, Integer>();
		sizeWaysGobV = new HashMap<Integer, Integer>();
		
		// TODO Auto-generated constructor stub
	}
	
	public boolean validCase(int x, int y ){//sert a rien
		if(world.marcheSurCase(x, y)) {
			return true;
		}
		return false;
	}
	
	public int calculCase(int x, int y){//return le numero de la case
		return (y*(world.GetWidthTabTiles())+x);
	}
	public int backToX(int numCase){
		return (numCase%(world.GetWidthTabTiles()));
	}
	public int backToY(int numCase) {
		return ((numCase-(backToX(numCase)))/(world.GetWidthTabTiles()));
	}
	
	
	public void findAWayGobT(){//BFS s'active uniquement quand le link sera a un certain emplacement
		ArrayList<Integer>Queue = new ArrayList<>();
		ArrayList<Integer>Past = new ArrayList<>();//d�ja pass�
		sizeWaysGobT.clear();
		int size = 0;//map's value
		int i=0;

		Queue.add(calculCase(link.getPersoCASE_X(), link.getPersoCASE_Y()));
		Past.add(calculCase(link.getPersoCASE_X(), link.getPersoCASE_Y()));
		
		int[][]currentCase ={{link.getPersoCASE_X()},{link.getPersoCASE_Y()}};
		
		while(Queue.size() != 0){//TW && (currentCase[0][0] != link.getPersoCASE_X() && currentCase[1][0] != link.getPersoCASE_Y())
			
			if(size>1) {
				size = sizeWaysGobT.get(calculCase(currentCase[0][0], currentCase[1][0]))+1;
				
			}
			else {
				size++;
			}
			if(world.marcheSurCase(currentCase[0][0], (currentCase[1][0]+1)) && Past.contains(calculCase(currentCase[0][0], currentCase[1][0]+1))==false ){//UP
				Queue.add(calculCase(currentCase[0][0], (currentCase[1][0]+1)));
				Past.add(calculCase(currentCase[0][0], (currentCase[1][0]+1)));
				sizeWaysGobT.put(calculCase(currentCase[0][0], (currentCase[1][0]+1)), size);				
			}
			if(world.marcheSurCase(currentCase[0][0], (currentCase[1][0]-1)) && Past.contains(calculCase(currentCase[0][0], currentCase[1][0]-1))==false ){//DOWN
				Queue.add(calculCase(currentCase[0][0], (currentCase[1][0]-1)));
				Past.add(calculCase(currentCase[0][0], (currentCase[1][0]-1)));
				sizeWaysGobT.put(calculCase(currentCase[0][0], (currentCase[1][0]-1)), size);
				
			}
			if(world.marcheSurCase((currentCase[0][0]+1), currentCase[1][0]) && Past.contains(calculCase(currentCase[0][0]+1, currentCase[1][0]))==false ){//LEFT
				Queue.add(calculCase((currentCase[0][0]+1), currentCase[1][0]));
				Past.add(calculCase((currentCase[0][0]+1), currentCase[1][0]));
				sizeWaysGobT.put(calculCase((currentCase[0][0]+1), currentCase[1][0]), size);
			}
			if(world.marcheSurCase((currentCase[0][0]-1), currentCase[1][0]) && Past.contains(calculCase(currentCase[0][0]-1, currentCase[1][0]))==false ){//LEFT
				Queue.add(calculCase((currentCase[0][0]-1), currentCase[1][0]));
				Past.add(calculCase((currentCase[0][0]-1), currentCase[1][0]));
				sizeWaysGobT.put(calculCase((currentCase[0][0]-1), currentCase[1][0]), size);
			}
			
			//enl�ve la case en tete de file
			Queue.remove(0);
			//change la case case observ�e
			if(Queue.size()>0){
			currentCase[0][0] = backToX(Queue.get(0));
			currentCase[1][0] =	backToY(Queue.get(0));
			}
		}
	}
	
	public void findAWayGobV(){//BFS s'active uniquement quand le link sera a un certain emplacement
		ArrayList<Integer>Queue = new ArrayList<>();
		ArrayList<Integer>Past = new ArrayList<>();//d�ja pass�
		sizeWaysGobV.clear();
		int size = 0;//map's value
		int i=0;

		Queue.add(calculCase(link.getPersoCASE_X(), link.getPersoCASE_Y()));
		Past.add(calculCase(link.getPersoCASE_X(), link.getPersoCASE_Y()));
		
		int[][]currentCase ={{link.getPersoCASE_X()},{link.getPersoCASE_Y()}};
		
		while(Queue.size() != 0){//TW && (currentCase[0][0] != link.getPersoCASE_X() && currentCase[1][0] != link.getPersoCASE_Y())
			size++;
			if(size>1)
				size = sizeWaysGobV.get(calculCase(currentCase[0][0], currentCase[1][0]))+1;
			
			if(world.inMap(currentCase[0][0], currentCase[1][0]+1) && Past.contains(calculCase(currentCase[0][0], currentCase[1][0]+1))==false ){//UP
				Queue.add(calculCase(currentCase[0][0], currentCase[1][0]+1));
				Past.add(calculCase(currentCase[0][0], currentCase[1][0]+1));
				sizeWaysGobV.put(calculCase(currentCase[0][0], currentCase[1][0]+1),size);
			}
			
			if(world.inMap(currentCase[0][0], currentCase[1][0]-1) && Past.contains(calculCase(currentCase[0][0], currentCase[1][0]-1))==false ){//DOWN
				Queue.add(calculCase(currentCase[0][0], currentCase[1][0]-1));
				Past.add(calculCase(currentCase[0][0], currentCase[1][0]-1));
				sizeWaysGobV.put(calculCase(currentCase[0][0], currentCase[1][0]-1),size);
			}
			
			if(world.inMap(currentCase[0][0]+1, currentCase[1][0]) && Past.contains(calculCase(currentCase[0][0]+1, currentCase[1][0]))==false ){//LEFT
				Queue.add(calculCase(currentCase[0][0]+1, currentCase[1][0]));
				Past.add(calculCase(currentCase[0][0]+1, currentCase[1][0]));
				sizeWaysGobV.put(calculCase(currentCase[0][0]+1, currentCase[1][0]),size);
			}
			
			if(world.inMap(currentCase[0][0]-1, currentCase[1][0]) && Past.contains(calculCase(currentCase[0][0]-1, currentCase[1][0]))==false ){//LEFT
				Queue.add(calculCase(currentCase[0][0]-1, currentCase[1][0]));
				Past.add(calculCase(currentCase[0][0]-1, currentCase[1][0]));
				sizeWaysGobV.put(calculCase(currentCase[0][0]-1, currentCase[1][0]),size);
			}
			//enl�ve la case en tete de file
			Queue.remove(0);
			//change la case case observ�e
			if(Queue.size()>0){
			currentCase[0][0] = backToX(Queue.get(0));
			currentCase[1][0] =	backToY(Queue.get(0));
			}
		}
	}
	
	public void displaySizeWay() {
		System.out.println(sizeWaysGobV);
		System.out.println(sizeWaysGobT);
	}
	
	public HashMap<Integer, Integer> getTheWayGobT(){
		return sizeWaysGobT;
	}
	public HashMap<Integer, Integer> getTheWayGobV(){
		return sizeWaysGobV;
	}
	

}
