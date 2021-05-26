package application.modele;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class Goblins extends Personnage{

	private static int id = 1;
	
	public Goblins(double x, double y) {
		super(x, y, "G"+id);
		id++;
	}
	
	public void findMyLink(Link link){//s'active uniquement quand le link sera a un certain emplacement
		
	}

	public String getDirection() {
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
	@Override
	public void move(String direction) {
		if(direction.equalsIgnoreCase("Top"))
			this.setY(getY()-32);
		else if(direction.equalsIgnoreCase("Down"))
			this.setY(getY()+32);
		else if(direction.equalsIgnoreCase("Right"))
			this.setX(getX()+32);
		else if(direction.equalsIgnoreCase("Left"))
			this.setX(getX()-32);
		
	}

}
