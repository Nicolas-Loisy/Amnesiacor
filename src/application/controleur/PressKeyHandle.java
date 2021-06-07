package application.controleur;
//PAS REFACTORISÉ
import application.modele.Environnement;
import application.modele.Link;
import application.tools.BFS;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PressKeyHandle implements EventHandler<KeyEvent> {
	Link link;
	Rectangle linkView;
	Boolean pressed;
	long time;
	
	private Environnement world;
	
	public  PressKeyHandle(Link link, Environnement world, Rectangle linkvue) {
		this.link = link;
		this.linkView = linkvue;
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
		Image linkDer = new Image("File:img/LinkImage/stationnB/derriere.png");
		long start;
		if(pressed && e.getCode() == KeyCode.Z ){
			linkView.setFill(new ImagePattern(new Image("File:img/Link/notmove/derriere.png"),0, 0, 1, 1, true));
			if(world.marcheSurCase(link.getPersoCASE_X(), link.getPersoCASE_Y()-1)){
				link.move("Up");
				link.RecupHearts();
			}	
			this.pressed = false;
			link.setPersoTab();//X ET Y CASE
			return start = System.currentTimeMillis();
		}	
		
		else if (pressed && e.getCode() == KeyCode.S){
			linkView.setFill(new ImagePattern(new Image("file:img/1.png"),0, 0, 1, 1, true));
			if(world.marcheSurCase(link.getPersoCASE_X(), link.getPersoCASE_Y()+1)){
				link.move("Down");
				link.RecupHearts();
			}
			this.pressed = false;
			link.setPersoTab();
			return start = System.currentTimeMillis();
		}
		
		else if (pressed && e.getCode() == KeyCode.D){
			linkView.setFill(new ImagePattern(new Image("File:img/Link/notmove/droiteYeuxOuverts.png"),0, 0, 1, 1, true));
			if(world.marcheSurCase(link.getPersoCASE_X()+1, link.getPersoCASE_Y())){
				link.move("Right");
				link.RecupHearts();
			}
			this.pressed = false;
			link.setPersoTab();
			return start = System.currentTimeMillis();
		}
		
		else if (pressed && e.getCode() == KeyCode.Q){
			linkView.setFill(new ImagePattern(new Image("File:img/Link/notmove/gaucheYeuxOuverts.png"),0, 0, 1, 1, true));
			if(world.marcheSurCase(link.getPersoCASE_X()-1, link.getPersoCASE_Y())){
				link.move("Left");
				link.RecupHearts();
			}
			this.pressed = false;
			link.setPersoTab();
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
			link.attaque();
			
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
