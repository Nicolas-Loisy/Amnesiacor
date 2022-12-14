package application.modele;

import application.tools.BFS;
import javafx.beans.property.SimpleDoubleProperty;

public class Gvolants extends Goblins {
	
	public Gvolants(double x, double y ,Environnement world, BFS bfs,Link link) {
		super(x, y, world, bfs,link,10);
		this.degatsGive = 10;
	}

	public Gvolants( Environnement world, BFS bfs,Link link,int porte) {
		super(world,link,porte);
		this.degatsGive = 10;
		this.gobBfs = bfs;
		
	}
	@Override
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
	
	@Override
	public void chooseAway(){
		int caseInView;
		int LastCase = gobBfs.calculCase(this.getPersoCASE_X(), this.getPersoCASE_Y());
		int currentCase = gobBfs.calculCase(this.getPersoCASE_X(), this.getPersoCASE_Y());
		
			gobBfs.findAWayGobV();
			if(gobBfs.getTheWayGobV().get(currentCase)!=1) {
					
					caseInView = gobBfs.calculCase(getPersoCASE_X(), getPersoCASE_Y()-1);//up
				if(world.inMap(gobBfs.backToX(caseInView), gobBfs.backToY(caseInView))) {
					if ( gobBfs.getTheWayGobV().get(caseInView) < gobBfs.getTheWayGobV().get(currentCase)) {
						currentCase = caseInView;
					}
				}
				
					caseInView = gobBfs.calculCase(getPersoCASE_X(), getPersoCASE_Y()+1);//DOWN
				if(world.inMap(gobBfs.backToX(caseInView), gobBfs.backToY(caseInView))) {
					if ( gobBfs.getTheWayGobV().get(caseInView) < gobBfs.getTheWayGobV().get(currentCase)) {
						currentCase = caseInView;
					}
				}	
				
					caseInView = gobBfs.calculCase(getPersoCASE_X()+1, getPersoCASE_Y());//Right
				if(world.inMap(gobBfs.backToX(caseInView), gobBfs.backToY(caseInView))) {
					if ( gobBfs.getTheWayGobV().get(caseInView) < gobBfs.getTheWayGobV().get(currentCase)) {
						currentCase = caseInView;
					}
				}
							
					caseInView = gobBfs.calculCase(getPersoCASE_X()-1, getPersoCASE_Y());
				if(world.inMap(gobBfs.backToX(caseInView), gobBfs.backToY(caseInView))) {
					if ( gobBfs.getTheWayGobV().get(caseInView) < gobBfs.getTheWayGobV().get(currentCase)) {
						currentCase = caseInView;
					}
				}
				
			}
			else
				attaquerLink();
			//PERMET DE SET LES POSITIONS PIXELS
			if(currentCase == (LastCase-world.GetWidthTabTiles()) && world.availablePositionWalk(getX(),getY()-32)) {
				this.move("Up");
			}
			else if (currentCase== (LastCase+world.GetWidthTabTiles()) && world.availablePositionWalk(getX(),getY()+32)){
				this.move("Down");
				
			}
			else if(currentCase == LastCase+1 && world.availablePositionWalk(getX()+32,getY())) {
				this.move("Right");
			}
			else if (currentCase == LastCase-1 && world.availablePositionWalk(getX()-32,getY())) {
				this.move("Left");
			}
	}
		
	@Override
			public void move(){
				int currentCase = gobBfs.calculCase(this.getPersoCASE_X(), this.getPersoCASE_Y());
				gobBfs.findAWayGobV();
				if (gobBfs.getTheWayGobV().get(currentCase) <= this.porteDetecteLink){
					chooseAway();
				}
				else {
					getRandomDirection();
				}
			}
}
