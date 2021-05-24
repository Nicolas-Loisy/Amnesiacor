package application.controleur;

import application.modele.Link;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PressKeyHandle implements EventHandler<KeyEvent> {
	Link link;
	Boolean pressed;
	long time;
	public  PressKeyHandle(Link link) {
		this.link = link;
		this.pressed = true;
		this.time = System.currentTimeMillis();
		
	}

	@Override
	public void handle(KeyEvent e) {
		//
		long startTime = keyPressed(e);
		long stopTime = startTime - time;
		System.out.println(time +" et "+ startTime);
		System.out.println(stopTime);
		if( stopTime > 320 ) {//delais entre quand c'est plusieurs press
			keyReleased(e);
			time = System.currentTimeMillis();
		}
		
	}
	
	public long keyPressed(KeyEvent e) {
		long start;
		if(pressed && e.getCode() == KeyCode.Z ){
			link.move("Up");
			this.pressed = false;
			return start = System.currentTimeMillis();
		}	
		else if (pressed && e.getCode() == KeyCode.S){
			link.move("Down");
			this.pressed = false;
			return start = System.currentTimeMillis();
		}
		else if (pressed && e.getCode() == KeyCode.D){
			link.move("Right");
			this.pressed = false;
			return start = System.currentTimeMillis();
		}
		else if (pressed && e.getCode() == KeyCode.Q){
			link.move("Left");
			this.pressed = false;
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
