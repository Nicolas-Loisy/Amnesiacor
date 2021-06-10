package application.modele;

//PAS REFACTORISE

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

//import org.graalvm.compiler.word.Word;

import application.tools.BFS;

public class Goblins extends Personnage{
	private static int numGob = 1;
	protected BFS gobBfs;
	private Link monEnnemi;

	public Goblins(double x,double y,Environnement world, BFS bfs,Link link) {
		super(x, y, "G"+numGob,world,50);
		this.gobBfs = bfs;
		this.monEnnemi = link;
		numGob++;
	}
	public Goblins(Environnement world, BFS bfs,Link link) {
		super("G"+numGob, world, 50);
		this.gobBfs = bfs;
		this.monEnnemi = link;
		numGob++;
	}
	public Goblins(Environnement world,Link link) {
		super(world,"G"+numGob, 50);
		this.monEnnemi = link;
		numGob++;
	}

	public Link getMonEnnemi() {
		return this.monEnnemi;
	}
	
	public void attaquerLink() {
		
	}
	
	public void getRandomDirection() {
		int dx;
		int dy;
		do{
			dx = (int) (Math.random() * 3) - 1; // [-1;1]
			dy = (int) (Math.random() * 3) - 1;
		}while( (dx==1 && dy==1) || (dx==-1 && dy==1) || (dx==1 && dy==-1)|| (dx==-1 && dy==-1));
		
		if (dx != 0){
			if (dx == 1){
				this.move("Right");
			}
			else {
				this.move("Left");
			}
		}
		else if (dy != 0) {
			if (dy == 1){
				this.move("Up");
			}
			else {
				this.move("Down");			
			}
		}
	}
	
	public void chooseAway(){
		int caseInView;
		int LastCase = gobBfs.calculCase(this.getPersoCASE_X(), this.getPersoCASE_Y());
		int currentCase = gobBfs.calculCase(this.getPersoCASE_X(), this.getPersoCASE_Y());
		

			gobBfs.findAWayGobT();

			if(gobBfs.getTheWayGobT().get(currentCase)!=1){
				if(world.marcheSurCase(getPersoCASE_X(),getPersoCASE_Y()-1)){		
					caseInView = gobBfs.calculCase(getPersoCASE_X(), getPersoCASE_Y()-1);//up
					if ( gobBfs.getTheWayGobT().get(caseInView) < gobBfs.getTheWayGobT().get(currentCase)) {
						currentCase = caseInView;
					}
				}
				if(world.marcheSurCase(getPersoCASE_X(),getPersoCASE_Y()+1)) {
					caseInView = gobBfs.calculCase(getPersoCASE_X(), getPersoCASE_Y()+1);//DOWN
					if ( gobBfs.getTheWayGobT().get(caseInView) < gobBfs.getTheWayGobT().get(currentCase)) {
						currentCase = caseInView;
					}
				}
				if(world.marcheSurCase(getPersoCASE_X()+1,getPersoCASE_Y()))  {
					caseInView = gobBfs.calculCase(getPersoCASE_X()+1, getPersoCASE_Y());//Right
					if ( gobBfs.getTheWayGobT().get(caseInView) < gobBfs.getTheWayGobT().get(currentCase)) {
						currentCase = caseInView;
					}	
				}
				if(world.marcheSurCase(getPersoCASE_X()-1,getPersoCASE_Y())) {
					caseInView = gobBfs.calculCase(getPersoCASE_X()-1, getPersoCASE_Y());
					if ( gobBfs.getTheWayGobT().get(caseInView) < gobBfs.getTheWayGobT().get(currentCase)) {
						currentCase = caseInView;
					}
				}
			}
			//PERMET DE SET LES POSITIONS PIXELS
			if(currentCase == LastCase-20 && world.availablePositionWalk(getX(),getY()-32)) {
				this.move("Up");
			}
			else if (currentCase== LastCase+20 && world.availablePositionWalk(getX(),getY()+32)){
				this.move("Down");
				
			}
			else if(currentCase == LastCase+1 && world.availablePositionWalk(getX()+32,getY())) {
				this.move("Right");
			}
			else if (currentCase == LastCase-1 && world.availablePositionWalk(getX()-32,getY())) {
				this.move("Left");
			}
			//PERMET D'ACTUALISER LES POSITIONS CASES|| ATTENTION LISTENER OBSCELET CAR FONCTION getpersoTab fais la meme chose
			super.setPersoTab();
	}
	
	
	/*EN TRAVAUX*/
	public void move() {
		chooseAway();
		//getRandomDirection();
	}

}
