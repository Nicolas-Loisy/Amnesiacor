package application.controleur;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

//import com.sun.javafx.geom.Rectangle;
import com.sun.scenario.effect.impl.state.LinearConvolveKernel;

import application.Main;
import application.modele.Environnement;
import application.modele.Goblins;
import application.modele.Link;
import application.tools.JsonReader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.paint.ImagePattern;

public class Controleur implements Initializable {
	
	
	private Environnement world;
	
	
    @FXML
    private javafx.scene.layout.Pane Pane;//root
	@FXML
	private BorderPane BorderP;
	@FXML
    private TilePane TileMap;//map w/ tuilles

	//handlerScene
	private static final String linkURL = "file:img/1.png";
	private Link link;
	private Rectangle linkVue;
	
	private static final String goblinTerreURL = "file:img/Chevalier.gif";
	private static final String goblinVolantURL = "file:img/ChasupaVolant.gif";
	
	//GAMELOOP PART
	private Timeline gameLoop;
	private int temps;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/*SET THE WORD PART*/
		world = new Environnement(640,640,20,20);
		

		
		/*CREA LINK PART*/
		Image imgLink = new Image(linkURL);
		createLink(imgLink);
		/*CREA GOBLIN PART*/
		Image imgGobTer = new Image(goblinTerreURL);
		Image imgGobVol = new Image(goblinVolantURL);
		createGoblin(1, imgGobTer, imgGobVol);
		
		update();
		fillInMap("File:img/zeldaTileset.png");
		
		//GameLoop();
		//gameLoop.play();
		
		
		
		/*gameL*/
	}
	
	public void GameLoop(){
		
			gameLoop = new Timeline();
			temps = 0;
			gameLoop.setCycleCount(Timeline.INDEFINITE);
		
			KeyFrame kf = new KeyFrame(
				// on définit le FPS (nbre de frame par seconde)
				Duration.seconds(.017), 
				// on définit ce qui se passe à chaque frame 
				// c'est un eventHandler d'ou le lambda
				(ev ->{		
					if(temps==5000){//TW: REMPLACER PAR UN SI KEYCODE == ESCAPE OR FUTUR MENUS QUIT
					System.out.println("fini");
					gameLoop.stop();
					}
					else if (temps%50==0){
						System.out.println("un tour");
						update();
						emptyTheMap();
						fillInMap("file:img/zeldaTileset.png");
					}
					else {
						System.out.println("none");
					}
					temps++;
					})
				);
		gameLoop.getKeyFrames().add(kf);
	}
	public void update(){
		/*POSITION LINK PART*/
		moveHandle();
		linkVue.translateXProperty().bind(link.getxProporty());
		linkVue.translateYProperty().bind(link.getyProporty());
		/*POSITION GOBLIN PART*/
		for (Goblins g : world.getListeGoblins()) {
			g.move(g.getDirection());
			Pane.lookup("#"+g.getId()).translateXProperty().bind(g.getxProporty());
			Pane.lookup("#"+g.getId()).translateYProperty().bind(g.getyProporty());
		}
		
		
	}
	
	public void createLink(Image imageLink) {
		link = new Link(32, 16, "A");//crea link modele
		linkVue = new Rectangle(32, 42); //créa link vue
		linkVue.setFill(new ImagePattern(imageLink, 0, 0, 1, 1, true));
		linkVue.setId(link.getId());
		Pane.getChildren().add(linkVue);//add du link dans la map
		
	}
	public void createGoblin(int NumberOfGoblins,Image imageGterrestre, Image imageGvolants){
		//UN seul goblin
		Goblins goblin = new Goblins(94,32);
		Rectangle goblinVue = new Rectangle(64,74);
		goblinVue.setFill(new ImagePattern(imageGterrestre, 0, 0, 1, 1, true));
		goblinVue.setId(goblin.getId()); 
		world.addGoblins(goblin);
		Pane.getChildren().add(goblinVue);
		
		
		//PLUSIEURS GOBLIN
		/*for (int i = 0; i < NumberOfGoblins; i++) {
			world.addGoblins(new Goblins(94 , 32));
			Rectangle GoblinVue = new Rectangle(32,42);
			GoblinVue.setFill(new ImagePattern(imageGterrestre, 0, 0, 1, 1, true));
			GoblinVue.setId();
			
		}*/
		
		
	}
	
	public void emptyTheMap() {
		for (int i = 0; i < 400; i++) {
	        TileMap.getChildren().clear();
	    }
	}
	
	public void fillInMap(String imgEmp) {
		int [][] tab = null;
		double tile;
		double l,h;//colonnes lignes
		try {	
			tab = JsonReader.chargerTableau("img/minishMAP.json").clone();
			
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Image img = new Image(imgEmp);
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				
				tile = tab[i][j];//recup num tile
				
				l = (tile-1)%5; //calcul position en x
				h = (int) Math.ceil(tile/5)-1; //position en y
				
				ImageView imgv = new ImageView(img);
				Rectangle2D viewportRect = new Rectangle2D(l*32, h*32, 32, 32);//2st pour deplacer cadre, 2last taille cadre 
		        imgv.setViewport(viewportRect);
				imgv.setFitWidth(32);
				imgv.setFitHeight(32);
		        TileMap.getChildren().add(imgv);//add du tile
			}
			
	    }
		
		
	}
	
	//Methode avec BorderPane
	public void moveHandle() {
		boolean active ;
		String direction;
		/*KEY PRESS PART*/
		PressKeyHandle c = new PressKeyHandle(link);
		BorderP.addEventHandler(KeyEvent.KEY_PRESSED, c);
	}
	

}