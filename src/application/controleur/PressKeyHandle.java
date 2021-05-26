package application.controleur;

import application.modele.Environnement;
import application.modele.Link;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PressKeyHandle implements EventHandler<KeyEvent> {
	Link link;
	Boolean pressed;
	long time;
	
	private Environnement world;
	
	public  PressKeyHandle(Link link, Environnement world) {
		this.link = link;
		this.pressed = true;
		this.time = System.currentTimeMillis();
		this.world = world;
		
	}

	@Override
	public void handle(KeyEvent e) {
		//
		long startTime = keyPressed(e);
		long stopTime = startTime - time;
		/*System.out.println(time +" et "+ startTime);
		System.out.println(stopTime);*/
		if( stopTime > 325 ) {//delais entre quand c'est plusieurs press / 325ms
			keyReleased(e);
			time = System.currentTimeMillis();
		}
		
	}
	
	public long keyPressed(KeyEvent e) {
		long start;
		if(pressed && e.getCode() == KeyCode.Z ){
			if(world.marcheSurCase(link.getXcase(), link.getYcase()-1)){
				link.move("Up");
			}	
			this.pressed = false;
			link.getPersoTab();
			return start = System.currentTimeMillis();
		}	
		else if (pressed && e.getCode() == KeyCode.S){
			if(world.marcheSurCase(link.getXcase(), link.getYcase()+1)){
				link.move("Down");
			}
			this.pressed = false;
			link.getPersoTab();
			return start = System.currentTimeMillis();
		}
		else if (pressed && e.getCode() == KeyCode.D){
			if(world.marcheSurCase(link.getXcase()+1, link.getYcase())){
				link.move("Right");
			}
			this.pressed = false;
			link.getPersoTab();
			return start = System.currentTimeMillis();
		}
		else if (pressed && e.getCode() == KeyCode.Q){
			if(world.marcheSurCase(link.getXcase()-1, link.getYcase())){
				link.move("Left");
			}
			this.pressed = false;
			link.getPersoTab();
			return start = System.currentTimeMillis();
		}
		return start = System.currentTimeMillis();	
	}
	public void keyReleased(KeyEvent e) {
		if (e.KEY_RELEASED != null) {
			this.pressed=true;
			
		}
	}
	
	

}
