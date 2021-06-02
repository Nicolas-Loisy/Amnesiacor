package application.modele;
//PAS REFACTORISÉ
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

//import org.graalvm.compiler.word.Word;

import application.tools.BFS;

public class Goblins extends Personnage{

	private static int id = 1;
	private Boolean linkIsClose;
	private BFS gobBfs;
	
	public Goblins(Environnement world, BFS bfs,double x,double y) {
		
		super(x, y, "G"+id,world,50);
		startingPosition();
		linkIsClose = true;
		this.gobBfs = bfs;
		id++;
	}
	public Goblins(Environnement world, BFS bfs) {
		super(96, 176, "G"+id, world, 50);
		startingPosition();
	}
	
	public void startingPosition() {
		double x,y;
		
		do {
			x = 32 * (int)(Math.random()*11);
			y = -16 +(32 * (int)(Math.random()*11));
			System.out.println("bloqué");
		} while ( !(availablePosition(x, y)) || !(super.world.marcheSurCase((int)Math.floor(x/32), (int)Math.floor(y/32))));
		
		this.setX(x);
		this.setY(y);
	}
	
	public boolean availablePosition(double x, double y){
		for (Goblins g : super.world.getListeGoblins()){
			if(g.getX()==x && g.getY() == y) 
				return false;
		}
		return true;
	}
	
	
	public String getRandomDirection() {
		int dx;
		int dy;
		do{
			dx = (int) (Math.random() * 3) - 1; // [-1;1]
			dy = (int) (Math.random() * 3) - 1;
		
			
		}while( (dx==1 && dy==1) || (dx==-1 && dy==1) || (dx==1 && dy==-1)|| (dx==-1 && dy==-1));
		
		if (dx != 0){
			if (dx == 1){
				return "Right";
			}
			else {
				return "Left";
			}
		}
		else if (dy != 0) {
			if (dy == 1){
				return "Top";
			}
			else {
				return "Down";			
			}
		}
		else {
			return "none";
		}
	}
	
	public void chooseAway(){
		int caseInView;
		int LastCase = gobBfs.calculCase(this.getPersoCASE_X(), this.getPersoCASE_Y());
		int currentCase = gobBfs.calculCase(this.getPersoCASE_X(), this.getPersoCASE_Y());
		
			gobBfs.findAWay();
			
			if(gobBfs.getTheWay().get(currentCase)!=1) {
				if(world.marcheSurCase(getPersoCASE_X(),getPersoCASE_Y()-1)) {		
					caseInView = gobBfs.calculCase(getPersoCASE_X(), getPersoCASE_Y()-1);//up
					if ( gobBfs.getTheWay().get(caseInView) < gobBfs.getTheWay().get(currentCase)) {
						currentCase = caseInView;
					}
				}
				if(world.marcheSurCase(getPersoCASE_X(),getPersoCASE_Y()+1)) {
					caseInView = gobBfs.calculCase(getPersoCASE_X(), getPersoCASE_Y()+1);//DOWN
					if ( gobBfs.getTheWay().get(caseInView) < gobBfs.getTheWay().get(currentCase)) {
						currentCase = caseInView;
					}
				}
				if(world.marcheSurCase(getPersoCASE_X()+1,getPersoCASE_Y()))  {
					caseInView = gobBfs.calculCase(getPersoCASE_X()+1, getPersoCASE_Y());//Right
					if ( gobBfs.getTheWay().get(caseInView) < gobBfs.getTheWay().get(currentCase)) {
						currentCase = caseInView;
					}	
				}
				if(world.marcheSurCase(getPersoCASE_X()-1,getPersoCASE_Y())) {
					caseInView = gobBfs.calculCase(getPersoCASE_X()-1, getPersoCASE_Y());
					if ( gobBfs.getTheWay().get(caseInView) < gobBfs.getTheWay().get(currentCase)) {
						currentCase = caseInView;
					}
				}
			}
			
			//PERMET DE SET LES POSITIONS PIXELS
			if(currentCase == LastCase-20 && availablePosition(getX(),getY()-32)) {
				this.move("Up");
			}
			else if (currentCase== LastCase+20 && availablePosition(getX(),getY()+32)){
				this.move("Down");
				
			}
			else if(currentCase == LastCase+1 && availablePosition(getX()+32,getY())) {
				this.move("Right");
			}
			else if (currentCase == LastCase-1 && availablePosition(getX()-32,getY())) {
				this.move("Left");
			}
			
			//PERMET D'ACTUALISER LES POSITIONS CASES|| ATTENTION LISTENER OBSCELET CAR FONCTION getpersoTab fais la meme chose
			
			//with fonction
			super.getPersoTab();

			//with listener
			/*this.getxProporty().addListener((obs,old,nouv)->{
				this.setPersoCASE_X(this.calculCASEx());
			});
			this.getyProporty().addListener((obs,old,nouv)->{
				this.setPersoCASE_Y(this.calculCASEy());
			});*/
	}
	
	public void move() {
		
	}


	@Override
	public void attaque() {
		// TODO Auto-generated method stub
		
	}

}
