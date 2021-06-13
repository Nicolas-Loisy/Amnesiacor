package application.controleur;
//PAS REFACTORISE

import application.modele.Environnement;
import application.modele.Deplacables;
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
		long startTime = keyPressed(e);
		long stopTime = startTime - time;

		if( stopTime > 325) {//delais entre press / 325ms
			keyReleased(e);
			time = System.currentTimeMillis();
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.KEY_RELEASED != null) {
			this.pressed=true;
		}
	}

	public long keyPressed(KeyEvent e) {
		long start;
		//DEPLACEMENT LINK W/ OR W/out OBJ PART 
		if(pressed && e.getCode() == KeyCode.Z ){
			linkView.setFill(new ImagePattern(new Image("File:img/Link/notmove/derriere.png"),0, 0, 1, 1, true));
			if(world.marcheSurCase(link.getPersoCASE_X(), link.getPersoCASE_Y()-1)){
				link.deplaceObjet("Up");
				link.move("Up");
					link.RecupHearts();
			}	
			this.pressed = false;
			return start = System.currentTimeMillis();
		}	

		else if (pressed && e.getCode() == KeyCode.S){
			linkView.setFill(new ImagePattern(new Image("file:img/1.png"),0, 0, 1, 1, true));
			if(world.marcheSurCase(link.getPersoCASE_X(), link.getPersoCASE_Y()+1)){
				link.deplaceObjet("Down");
				link.move("Down");
					link.RecupHearts();
			}

			this.pressed = false;
			return start = System.currentTimeMillis();
		}

		else if (pressed && e.getCode() == KeyCode.D){
			linkView.setFill(new ImagePattern(new Image("File:img/Link/notmove/droiteYeuxOuverts.png"),0, 0, 1, 1, true));
			if(world.marcheSurCase(link.getPersoCASE_X()+1, link.getPersoCASE_Y())){
				link.deplaceObjet("Right");
				link.move("Right");
					link.RecupHearts();
			}
			this.pressed = false;
			return start = System.currentTimeMillis();
		}

		else if (pressed && e.getCode() == KeyCode.Q){
			linkView.setFill(new ImagePattern(new Image("File:img/Link/notmove/gaucheYeuxOuverts.png"),0, 0, 1, 1, true));
			if(world.marcheSurCase(link.getPersoCASE_X()-1, link.getPersoCASE_Y())){
				link.deplaceObjet("Left");
				link.move("Left");
					link.RecupHearts();
			}
			this.pressed = false;
			return start = System.currentTimeMillis();
		}



		//INVENTAIRE PART
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




		//OBJ GRAP PART
		else if (pressed && e.getCode() == KeyCode.E && link.detecteDeplacables() != null) {
			link.grabObjet(link.detecteDeplacables());
			this.pressed = false;
			return start = System.currentTimeMillis();
		}

		else if (pressed && e.getCode() == KeyCode.R) {
			link.lacher();
			this.pressed = false;
			return start = System.currentTimeMillis();
		}

		return start = System.currentTimeMillis();	
	}

	



}