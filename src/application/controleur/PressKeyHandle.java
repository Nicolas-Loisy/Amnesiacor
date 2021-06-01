package application.controleur;
//PAS REFACTORISÉ
import application.modele.Environnement;
import application.modele.Link;
import application.tools.BFS;
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
		if( stopTime > 200 ) {//delais entre quand c'est plusieurs press / 325ms
			keyReleased(e);
			time = System.currentTimeMillis();
		}
		
	}
	
	public long keyPressed(KeyEvent e) {
		long start;
		if(pressed && e.getCode() == KeyCode.Z ){
			if(world.marcheSurCase(link.getPersoCASE_X(), link.getPersoCASE_Y()-1)){
				link.move("Up");
			}	
			this.pressed = false;
			link.getPersoTab();
			return start = System.currentTimeMillis();
		}	
		else if (pressed && e.getCode() == KeyCode.S){
			if(world.marcheSurCase(link.getPersoCASE_X(), link.getPersoCASE_Y()+1)){
				link.move("Down");
			}
			this.pressed = false;
			link.getPersoTab();
			return start = System.currentTimeMillis();
		}
		else if (pressed && e.getCode() == KeyCode.D){
			if(world.marcheSurCase(link.getPersoCASE_X()+1, link.getPersoCASE_Y())){
				link.move("Right");
			}
			this.pressed = false;
			link.getPersoTab();
			return start = System.currentTimeMillis();
		}
		else if (pressed && e.getCode() == KeyCode.Q){
			if(world.marcheSurCase(link.getPersoCASE_X()-1, link.getPersoCASE_Y())){
				link.move("Left");
			}
			this.pressed = false;
			link.getPersoTab();
			return start = System.currentTimeMillis();
		}
		
		//Test Inventaire
		else if (pressed && e.getCode() == KeyCode.NUMPAD1){
			link.gestionEquipement(0);
			
			this.pressed = false;
			return start = System.currentTimeMillis();
		}
		else if (pressed && e.getCode() == KeyCode.NUMPAD2){
			link.gestionEquipement(1);
			
			this.pressed = false;
			return start = System.currentTimeMillis();
		}
		else if (pressed && e.getCode() == KeyCode.SPACE){
			link.attaque2();
			
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
