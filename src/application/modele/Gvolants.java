package application.modele;

import application.tools.BFS;

public class Gvolants extends Goblins {
	
	public Gvolants(double x, double y ,Environnement world, BFS bfs) {
		super(x, y, world, bfs);
	}


	public Gvolants(int x, int y, Environnement world, BFS bfs) {
		super(x, y,world, bfs);
	}

	public Gvolants( Environnement world, BFS bfs) {
		super(world, bfs);



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
			//PERMET DE SET LES POSITIONS PIXELS
			if(currentCase == LastCase-20 && world.availablePosition(getX(),getY()-32)) {
				this.move("Up");
			}
			else if (currentCase== LastCase+20 && world.availablePosition(getX(),getY()+32)){
				this.move("Down");
				
			}
			else if(currentCase == LastCase+1 && world.availablePosition(getX()+32,getY())) {
				this.move("Right");
			}
			else if (currentCase == LastCase-1 && world.availablePosition(getX()-32,getY())) {
				this.move("Left");
			}
			//PERMET D'ACTUALISER LES POSITIONS CASES|| ATTENTION LISTENER OBSCELET CAR FONCTION getpersoTab fais la meme chose
			super.setPersoTab();
	}
}
